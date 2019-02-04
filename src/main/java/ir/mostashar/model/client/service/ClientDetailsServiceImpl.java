package ir.mostashar.model.client.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.repository.RoleRepository;
import ir.mostashar.model.user.RoleName;
import ir.mostashar.model.user.dto.SignUpForm;
import ir.mostashar.security.jwt.JwtProvider;
import ir.mostashar.util.DataUtil;
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
public class ClientDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

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
        System.out.println("Log---------------existsByPhoneNumber " + phoneNumber);

        Optional<Boolean> aBoolean = userRepository.existsUserByMobileNumber(phoneNumber);
        if (aBoolean.isPresent())
            return aBoolean.get();
        else
            return false;
    }

    public void registerPhoneNumberAndRole(SignUpForm signUpForm) {
        System.out.println("Log---------registerPhoneNumberAndRole");
        UUID uuid = UUID.randomUUID();
        User newUser = new User(uuid, Long.valueOf(signUpForm.getPhoneNumber()));
        Client client = new Client(Long.valueOf(signUpForm.getPhoneNumber()));
        client.setUid(uuid);

        Set<String> strRoles = signUpForm.getRole();
        Set<Role> roles = new HashSet<>();

        System.out.println("Random "+DataUtil.genarateRandomNumber());
        System.out.println("Random "+DataUtil.genarateRandomNumber());
        strRoles.forEach(role -> {
            switch (role) {
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
        clientRepository.save(client);
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
