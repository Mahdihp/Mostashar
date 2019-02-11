package ir.mostashar.model.client.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.ValidateCode;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.repository.RoleRepository;
import ir.mostashar.model.role.RoleName;
import ir.mostashar.model.client.dto.SignUpForm;
import ir.mostashar.security.jwt.JwtProvider;
import ir.mostashar.security.jwt.JwtResponse;
import ir.mostashar.util.Constants;
import ir.mostashar.util.DataUtil;
import ir.mostashar.util.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private SmsService smsService;

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
        if (aBoolean.isPresent()) {
            return aBoolean.get();
        }
        return false;
    }

    public Optional<String> registerPhoneNumberAndRole(SignUpForm signUpForm) {
        UUID uuid = UUID.randomUUID();
        Client client = new Client();
        client.setMobileNumber(Long.valueOf(signUpForm.getPhoneNumber()));
        String code = DataUtil.genarateRandomNumber();
        client.setVerificationCode(code);
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
        Client userSave = clientRepository.save(client);
        if (userSave != null) {
            smsService.sendSms(signUpForm.getPhoneNumber(), Constants.KEY_SEND_VERIFY_CODE + "\n" + code);
            return Optional.of(uuid.toString());
        } else
            return Optional.empty();
    }

    public JwtResponse generateToken(@Valid @RequestBody ValidateCode validateCode) {
        Optional<User> userOptional = userRepository.findByUid(UUID.fromString(validateCode.getUserid()));
        if (userOptional.isPresent()) {
            System.out.println("Log---------2--generateToken " + userOptional.get().toString());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userOptional.get().getUsername(),
                            userOptional.get().getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateJwtToken(authentication);
            return new JwtResponse(jwt);
        }
        return null;
    }

    public Optional<User> findUserIdAndCode(String userid, String code) {
        System.out.println("Log----------findUserIdAndCode " + userid + "  " + code);
        Optional<User> user = userRepository.findUserByUidAndVerificationCode(UUID.fromString(userid), code);
        return user;
//        if (user.isPresent())
//            return user.get();
//        else
//            return null;
    }

    public void activeUser(boolean isactive, UUID userid) {
        Optional<User> user = userRepository.findByUid(userid);
        if (user.isPresent()) {
            user.get().setActive(isactive);
            user.get().setVerificationCode("-1");
            userRepository.save(user.get());
        }
    }


    public Optional<User> findByUserid(UUID userid) {
        Optional<User> user = userRepository.findByUid(userid);
        return user;
//        if (user.isPresent())
//            return user.get();
//        else
//            return null;
    }

    public Optional<Client> findByClientId(UUID userid) {
        Optional<Client> client = clientRepository.findByUid(userid);
        return client;
    }
}
