package ir.mostashar.model.client.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.repository.RoleRepository;
import ir.mostashar.model.user.RoleName;
import ir.mostashar.model.client.dto.SignUpForm;
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
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

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

    public String registerPhoneNumberAndRole(SignUpForm signUpForm) {
        UUID uuid = UUID.randomUUID();
        Client client = new Client();
        client.setMobileNumber(Long.valueOf(signUpForm.getPhoneNumber()));
        client.setVerificationCode(DataUtil.genarateRandomNumber());
        client.setTel(Long.valueOf(signUpForm.getPhoneNumber()));
        client.setUid(uuid);

        Set<String> strRoles = signUpForm.getRole();
        Set<Role> roles = new HashSet<>();

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
                    Role userRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
        client.setRoles(roles);
        clientRepository.save(client);
        // Send SMS to Client
        return uuid.toString();
    }


    public User findUUIDAndCode(String userid, String code) {
        Optional<User> user = userRepository.findUserByUidAndVerificationCode(UUID.fromString(userid), code);
        if (user.isPresent())
            return user.get();
        else
            return null;
    }

    public void setActiveUser(boolean isactive, UUID userid) {
        Optional<User> user = userRepository.findByUid(userid);
        if (user.isPresent()) {
            user.get().setActive(isactive);
            user.get().setVerificationCode("-1");
            userRepository.save(user.get());
        }
    }

}
