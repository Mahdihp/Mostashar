package ir.mostashar.model.client.service;

import ir.mostashar.model.adviceType.service.AdviceTypeService;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.ClientDTO;
import ir.mostashar.model.client.dto.ClientProfileForm;
import ir.mostashar.model.client.dto.ListClientDTO;
import ir.mostashar.model.client.repository.ClientRepo;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.RoleName;
import ir.mostashar.model.role.repository.RoleRepo;
import ir.mostashar.model.user.User;
import ir.mostashar.model.wallet.Wallet;
import ir.mostashar.model.wallet.service.WalletService;
import ir.mostashar.utils.Constants;
import ir.mostashar.utils.DataUtil;
import ir.mostashar.utils.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {


    @Autowired
    private ClientRepo clientRepo;


    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private WalletService walletService;

    @Autowired
    private SmsService smsService;

    @Autowired
    AdviceTypeService adviceTypeService;


    public Optional<Client> findClientByUidAndActive(String userid, boolean active) {
        Optional<Client> client = clientRepo.findClientByUidAndActive(UUID.fromString(userid), active);
        if (client.isPresent())
            return Optional.ofNullable(client.get());
        else
            return Optional.empty();
    }

    public boolean updateClient(ClientProfileForm cpForm) {
        Optional<Client> client = clientRepo.findByUid(UUID.fromString(cpForm.getClientId()));
        if (client.isPresent()) {
            client.get().setFirstName(cpForm.getFirstName());
            client.get().setLastName(cpForm.getLastName());
            client.get().setFatherName(cpForm.getFatherName());
            client.get().setNationalId(cpForm.getNationalId());
            client.get().setBirthDate(cpForm.getBirthDate());
//            client.get().setAvatarHashcode(cpForm.getAvatarHashcode());
//            client.get().setActive(cpForm.getActive());
            client.get().setMobileNumber(Long.valueOf(cpForm.getMobileNumber()));
            client.get().setJobTitle(cpForm.getJobTitle());
            client.get().setAddress(cpForm.getAddress());
            client.get().setPostalCode(cpForm.getPostalCode());
            client.get().setFieldOfStudy(cpForm.getFieldOfStudy());
            client.get().setTel(Long.valueOf(cpForm.getTel()));
            clientRepo.save(client.get());
        }
        return false;
    }

    public Optional<ListClientDTO> findAllListClientDTO() {
        List<Client> clients = clientRepo.findAll();
        if (clients != null) {
            ListClientDTO lbcDTO = new ListClientDTO();
            lbcDTO.setStatus(HttpStatus.OK.value());
            lbcDTO.setMessage(Constants.KEY_SUCESSE);
            List<ClientDTO> dtoList = new ArrayList<>();
            for (Client client : clients) {
                ClientDTO bcDTO = new ClientDTO();
                bcDTO.setClientId(client.getUid().toString());
                bcDTO.setFirstName(client.getFirstName());
                bcDTO.setLastName(client.getLastName());
                bcDTO.setFatherName(client.getFatherName());
                bcDTO.setUsername(client.getUsername());
                bcDTO.setPassword(client.getPassword());
                bcDTO.setNationalId(client.getNationalId());
                bcDTO.setBirthDate(client.getBirthDate());
                bcDTO.setScore(client.getScore());
                bcDTO.setAvatarHashcode(client.getAvatarHashcode());
                bcDTO.setActive(client.getActive());
                bcDTO.setMobileNumber(client.getMobileNumber());
                bcDTO.setJobTitle(client.getJobTitle());
                bcDTO.setAddress(client.getAddress());
                bcDTO.setPostalCode(client.getPostalCode());
                bcDTO.setFieldOfStudy(client.getFieldOfStudy());
                bcDTO.setTel(client.getTel());
                bcDTO.setWalletId(client.getWallet().getUid().toString());
//                bcDTO.setVerificationCode(client.getVerificationCode());
                dtoList.add(bcDTO);

            }
            lbcDTO.setData(dtoList);
            return Optional.ofNullable(lbcDTO);
        }
        return Optional.empty();
    }

    public Optional<ClientDTO> findClientDTOByUid(String userid) {
        Optional<Client> client = clientRepo.findClientByUidAndActive(UUID.fromString(userid), true);
        if (client.isPresent()) {
            ClientDTO bcDTO = new ClientDTO();
            bcDTO.setStatus(HttpStatus.OK.value());
            bcDTO.setMessage(Constants.KEY_SUCESSE);

            bcDTO.setClientId(client.get().getUid().toString());
            bcDTO.setFirstName(client.get().getFirstName());
            bcDTO.setLastName(client.get().getLastName());
            bcDTO.setFatherName(client.get().getFatherName());
            bcDTO.setNationalId(client.get().getNationalId());
            bcDTO.setBirthDate(client.get().getBirthDate());
            bcDTO.setScore(client.get().getScore());
            bcDTO.setAvatarHashcode(client.get().getAvatarHashcode());
            bcDTO.setActive(client.get().getActive());
            bcDTO.setMobileNumber(client.get().getMobileNumber());
            bcDTO.setJobTitle(client.get().getJobTitle());
            bcDTO.setAddress(client.get().getAddress());
            bcDTO.setPostalCode(client.get().getPostalCode());
            bcDTO.setFieldOfStudy(client.get().getFieldOfStudy());
            bcDTO.setTel(client.get().getTel());
            bcDTO.setWalletId(client.get().getWallet().getUid().toString());

//            bcDTO.setVerificationCode(client.get().getVerificationCode());
            return Optional.ofNullable(bcDTO);
        }
        return Optional.empty();
    }


    public void deleteClient(String mobilenumber) {
        System.out.println("Log---deleteClient--------------------:"+mobilenumber);
        Optional<Client> client = clientRepo.findByMobileNumber(Long.parseLong(mobilenumber));
        if (client.isPresent())
            clientRepo.delete(client.get());
    }

    public Optional<Client> findByMobileNumber(String mobileNumber) {
        Optional<Client> client = clientRepo.findByMobileNumber(Long.valueOf(mobileNumber));
        if (client.isPresent())
            return Optional.ofNullable(client.get());
        else
            return Optional.empty();
    }

    public boolean existsMobileNumber(long phoneNumber) {
        Optional<Boolean> aBoolean = clientRepo.existsUserByMobileNumber(phoneNumber);
        if (aBoolean.isPresent())
            return aBoolean.get();
        else
            return false;
    }

    public Optional<UUID> registerUser(String phoneNumber) {

        Set<Role> roles = new HashSet<>();
        Role clientRole = roleRepo.findByName(RoleName.ROLE_CLIENT)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(clientRole);
        return saveClient(phoneNumber, roles);
    }


    private Optional<UUID> saveClient(String phoneNumber, Set<Role> roles) {
        Client client = new Client();
        UUID uuid = UUID.randomUUID();
        String code = DataUtil.genarateRandomNumber();
        client.setVerificationCode(code);
        client.setTel(Long.valueOf(phoneNumber));
        client.setUid(uuid);


        String user = DataUtil.generateAlphaNumericRandomUserPass(8);
//        String pass = DataUtil.generateAlphaNumericRandomUserPass(5);
        System.out.println("Log---saveClient-username-------------------:"+user);

        client.setMobileNumber(Long.valueOf(phoneNumber));
        client.setUsername(user);
        client.setPassword(encoder.encode(user));

        client.setScore(10);

        client.setRoles(roles);
        Client userSave = clientRepo.save(client);

        if (userSave != null) {
            smsService.sendSms(phoneNumber, Constants.KEY_SEND_VERIFY_CODE + "\n" + code);
            return Optional.of(uuid);
        }
        return Optional.empty();
    }

    public UUID createWallletUser(User user) {
        Optional<Wallet> wallet = walletService.findByUid(user.getUid().toString());
        UUID uuid;
        if (!wallet.isPresent()) {
            Wallet newWallet = new Wallet();
            uuid = UUID.randomUUID();
            newWallet.setUid(uuid);
            newWallet.setValue(0);
            newWallet.setUser(user);
//            newWallet.setOrganization(user);
            walletService.saveWallet(newWallet);
            return uuid;
        }
        return null;
    }


    public Optional<Client> findByUserUidAndCode(String uid, String code) {
        System.out.println("Log----------findByUserUidAndCode " + uid + "  " + code);
        Optional<Client> client = clientRepo.findByUidAndVerificationCode(UUID.fromString(uid), code);
        if (client.isPresent())
            return Optional.ofNullable(client.get());
        else
            return Optional.empty();
    }

    public Optional<Client> findUserByUid(String uid) {
        Optional<Client> client = clientRepo.findByUid(UUID.fromString(uid));
        if (client.isPresent())
            return Optional.ofNullable(client.get());
        else
            return Optional.empty();
    }

    public boolean activateUser(boolean isactive, UUID userid) {
        Optional<Client> client = clientRepo.findByUid(userid);
        if (client.isPresent()) {
            client.get().setActive(isactive);
            client.get().setVerificationCode("-1");
            clientRepo.save(client.get());
            return true;
        }
        return false;
    }
    public void updateCodeCerify(String mobileNumber,String code){
        Optional<Client> client = clientRepo.findByMobileNumber(Long.parseLong(mobileNumber));
        if (client.isPresent()){
            client.get().setVerificationCode(code);
            client.get().setUid(UUID.randomUUID());
            clientRepo.save(client.get());
        }
    }

    public void reSendCode(String mobileNumber) {
        String code = DataUtil.genarateRandomNumber();
        updateCodeCerify(mobileNumber,code);
        smsService.sendSms(mobileNumber, Constants.KEY_SEND_VERIFY_CODE + "\n" + code);
    }

    public void addScore(String lawyerUid, int score) {
        Optional<Client> client = clientRepo.findByUid(UUID.fromString(lawyerUid));
        if (client.isPresent()) {
            int totalScore = client.get().getScore();
            totalScore += score;
            client.get().setScore(totalScore);
            clientRepo.save(client.get());
        }
    }
}
