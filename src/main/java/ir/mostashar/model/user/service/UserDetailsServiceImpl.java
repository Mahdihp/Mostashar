package ir.mostashar.model.user.service;

import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.repository.RoleRepository;
import ir.mostashar.model.user.RoleName;
import ir.mostashar.model.user.dto.SignUpForm;
import ir.mostashar.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or password : " + username)
                );

        return UserPrinciple.build(user);
    }

    public boolean existsByPhoneNumber(long phoneNumber) {
        System.out.println("Log---------------existsByPhoneNumber "+phoneNumber);
        return userRepository.existsUserByMobileNumber(phoneNumber);
    }

    public void registerPhoneNumberAndRole(SignUpForm signUpForm){
        System.out.println("Log---------registerPhoneNumberAndRole");
        User newUser=new User(UUID.randomUUID(),Long.valueOf(signUpForm.getPhoneNumber()));
        Set<String> strRoles = signUpForm.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch(role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "lawyer":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_LAWYER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
//                    Optional<Role> byName = roleRepository.findByName(RoleName.ROLE_CLIENT);
//                    System.out.println("Log----------"+byName.isPresent());
                    Role userRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
        newUser.setRoles(roles);
        userRepository.save(newUser);
    }


    /*@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String usernameOrMobileNumber) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(usernameOrMobileNumber);

		if(user == null){
			user = userRepository.findByMobileNumber(usernameOrMobileNumber);
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}*/

}
