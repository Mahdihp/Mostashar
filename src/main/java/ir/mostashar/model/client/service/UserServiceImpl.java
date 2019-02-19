package ir.mostashar.model.client.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.*;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepository;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.repository.RoleRepository;
import ir.mostashar.model.role.RoleName;
import ir.mostashar.security.jwt.JwtProvider;
import ir.mostashar.security.jwt.JwtResponse;
import ir.mostashar.utils.Constants;
import ir.mostashar.utils.DataUtil;
import ir.mostashar.utils.SmsService;
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
    private LawyerRepository lawyerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;


    @Autowired
    private JwtProvider jwtProvider;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or password : " + username)
                );
        return UserPrinciple.build(user);
    }

    public JwtResponse generateToken(@Valid @RequestBody ValidateCode validateCode) {
        Optional<User> userOptional = userRepository.findUserByUidAndVerificationCode(UUID.fromString(validateCode.getUserid()), validateCode.getCode());

        if (userOptional.isPresent()) {

            System.out.println("Log---------2--generateToken " + userOptional.get().getUsername());
            System.out.println("Log---------2--generateToken " + userOptional.get().getPassword());


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userOptional.get().getUsername(),
                            "1")
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateJwtToken(authentication);
            return new JwtResponse(jwt);
        }
        return null;
    }

    public boolean existsPhoneNumber(long phoneNumber) {
        Optional<Boolean> aBoolean = userRepository.existsUserByMobileNumber(phoneNumber);
        if (aBoolean.isPresent())
            return aBoolean.get();
        else
            return false;
    }

    public Optional<String> registerUser(SignUpForm signUpForm, Role role) {

        Set<Role> roles = new HashSet<>();
        switch (role.getName()) {
//            case ROLE_ADMIN:
//                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                roles.add(adminRole);
//
//                break;
            case ROLE_LAWYER:
                Role lawyerRole = roleRepository.findByName(RoleName.ROLE_LAWYER)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(lawyerRole);
                return saveLawyer(signUpForm, roles);
            case ROLE_CLIENT:
                Role clientRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(clientRole);
                return saveClient(signUpForm, roles);

//            case ROLE_RESELLER:
//                Role resellerRole = roleRepository.findByName(RoleName.ROLE_RESELLER)
//                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                roles.add(resellerRole);
//                break;
            default:
                return null;
        }
    }

    private Optional<String> saveLawyer(SignUpForm signUpForm, Set<Role> roles) {
        Lawyer lawyer = new Lawyer();
        UUID uuid = UUID.randomUUID();
        lawyer.setMobileNumber(Long.valueOf(signUpForm.getPhoneNumber()));
        String code = DataUtil.genarateRandomNumber();
        lawyer.setVerificationCode(code);
        lawyer.setUid(uuid);

        lawyer.setUsername(DataUtil.generateAlphaNumericRandomUserPass(8));
        lawyer.setPassword(DataUtil.generateNumericRandomUserPass(8));

        lawyer.setRoles(roles);
        Lawyer userSave = lawyerRepository.save(lawyer);
        if (userSave != null) {
            smsService.sendSms(signUpForm.getPhoneNumber(), Constants.KEY_SEND_VERIFY_CODE + "\n" + code);
            return Optional.of(uuid.toString());
        } else
            return Optional.empty();
    }

    private Optional<String> saveClient(SignUpForm signUpForm, Set<Role> roles) {
        Client client = new Client();
        UUID uuid = UUID.randomUUID();
        client.setMobileNumber(Long.valueOf(signUpForm.getPhoneNumber()));
        String code = DataUtil.genarateRandomNumber();
        client.setVerificationCode(code);
        client.setTel(Long.valueOf(signUpForm.getPhoneNumber()));
        client.setUid(uuid);

        String user = DataUtil.generateAlphaNumericRandomUserPass(8);
        System.out.println("Log--------------saveClient " + user);
        client.setUsername(user);
        client.setPassword(encoder.encode("1"));

        client.setRoles(roles);
        Client userSave = clientRepository.save(client);
        if (userSave != null) {
            smsService.sendSms(signUpForm.getPhoneNumber(), Constants.KEY_SEND_VERIFY_CODE + "\n" + code);
            return Optional.of(uuid.toString());
        } else
            return Optional.empty();
    }


    public Optional<User> findUserIdAndCode(String userid, String code) {
        System.out.println("Log----------findUserIdAndCode " + userid + "  " + code);
        Optional<User> user = userRepository.findUserByUidAndVerificationCode(UUID.fromString(userid), code);
        if (user.isPresent())
            return Optional.ofNullable(user.get());
        else
            return Optional.empty();
    }

    public void activeUser(boolean isactive, UUID userid) {
        Optional<User> user = userRepository.findUserByUid(userid);
        if (user.isPresent()) {
            user.get().setActive(isactive);
            user.get().setVerificationCode("-1");
            userRepository.save(user.get());
        }
    }


}
