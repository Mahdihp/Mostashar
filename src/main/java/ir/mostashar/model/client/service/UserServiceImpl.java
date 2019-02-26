package ir.mostashar.model.client.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.*;
import ir.mostashar.model.client.repository.ClientRepo;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepo;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.repository.RoleRepo;
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
import ir.mostashar.model.user.repository.UserRepo;
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
    private UserRepo userRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private LawyerRepo lawyerRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder encoder;


    @Autowired
    private JwtProvider jwtProvider;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username or password : " + username)
                );
        return UserPrinciple.build(user);
    }

    public JwtResponse generateToken(@Valid @RequestBody ValidateCode validateCode) {
        Optional<User> userOptional = userRepo.findUserByUidAndVerificationCode(UUID.fromString(validateCode.getUserId()), validateCode.getCode());

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
        Optional<Boolean> aBoolean = userRepo.existsUserByMobileNumber(phoneNumber);
        if (aBoolean.isPresent())
            return aBoolean.get();
        else
            return false;
    }

    public Optional<String> registerUser(String phoneNumber, Role role) {

        Set<Role> roles = new HashSet<>();
        switch (role.getName()) {
//            case ROLE_ADMIN:
//                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                roles.add(adminRole);
//
//                break;
            case ROLE_LAWYER:
                Role lawyerRole = roleRepo.findByName(RoleName.ROLE_LAWYER)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(lawyerRole);
                return saveLawyer(phoneNumber, roles);
            case ROLE_CLIENT:
                Role clientRole = roleRepo.findByName(RoleName.ROLE_CLIENT)
                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(clientRole);
                return saveClient(phoneNumber, roles);

//            case ROLE_RESELLER:
//                Role resellerRole = roleRepository.findByName(RoleName.ROLE_RESELLER)
//                        .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                roles.add(resellerRole);
//                break;
            default:
                return null;
        }
    }

    private Optional<String> saveLawyer(String phoneNumber, Set<Role> roles) {
        Lawyer lawyer = new Lawyer();
        UUID uuid = UUID.randomUUID();
        lawyer.setMobileNumber(Long.valueOf(phoneNumber));
        String code = DataUtil.genarateRandomNumber();
        lawyer.setVerificationCode(code);
        lawyer.setUid(uuid);

        lawyer.setUsername(DataUtil.generateAlphaNumericRandomUserPass(8));
        lawyer.setPassword(DataUtil.generateNumericRandomUserPass(8));

        lawyer.setRoles(roles);
        Lawyer userSave = lawyerRepo.save(lawyer);
        if (userSave != null) {
            smsService.sendSms(String.valueOf(phoneNumber), Constants.KEY_SEND_VERIFY_CODE + "\n" + code);
            return Optional.of(uuid.toString());
        } else
            return Optional.empty();
    }

    private Optional<String> saveClient(String phoneNumber, Set<Role> roles) {
        Client client = new Client();
        UUID uuid = UUID.randomUUID();
        client.setMobileNumber(Long.valueOf(phoneNumber));
        String code = DataUtil.genarateRandomNumber();
        client.setVerificationCode(code);
        client.setTel(Long.valueOf(phoneNumber));
        client.setUid(uuid);

        String user = DataUtil.generateAlphaNumericRandomUserPass(8);
        client.setUsername(user);
        client.setPassword(encoder.encode("1"));

        client.setRoles(roles);
        Client userSave = clientRepo.save(client);
        if (userSave != null) {
            smsService.sendSms(String.valueOf(phoneNumber), Constants.KEY_SEND_VERIFY_CODE + "\n" + code);
            return Optional.of(uuid.toString());
        } else
            return Optional.empty();
    }


    public Optional<User> findUserIdAndCode(String userid, String code) {
        System.out.println("Log----------findUserIdAndCode " + userid + "  " + code);
        Optional<User> user = userRepo.findUserByUidAndVerificationCode(UUID.fromString(userid), code);
        if (user.isPresent())
            return Optional.ofNullable(user.get());
        else
            return Optional.empty();
    }

    public Optional<User> findUserByUid(String uid) {
        Optional<User> user = userRepo.findUserByUid(UUID.fromString(uid));
        if (user.isPresent())
            return Optional.ofNullable(user.get());
        else
            return Optional.empty();
    }

    public void activateUser(boolean isactive, UUID userid) {
        Optional<User> user = userRepo.findUserByUid(userid);
        if (user.isPresent()) {
            user.get().setActive(isactive);
            user.get().setVerificationCode("-1");
            userRepo.save(user.get());
        }
    }


}
