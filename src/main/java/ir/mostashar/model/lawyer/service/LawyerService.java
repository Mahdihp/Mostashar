package ir.mostashar.model.lawyer.service;

import ir.mostashar.model.acceptRequest.AcceptRequest;
import ir.mostashar.model.acceptRequest.service.AcceptRequestService;
import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.service.AdviceTypeService;
import ir.mostashar.model.call.Call;
import ir.mostashar.model.call.service.CallService;
import ir.mostashar.model.expertise.dto.ExpertiseDTO;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
import ir.mostashar.model.lawyer.dto.LawyerProfileForm;
import ir.mostashar.model.lawyer.dto.ListLawyerDTO;
import ir.mostashar.model.lawyer.repository.LawyerRepo;
import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.organization.service.OrganizationService;
import ir.mostashar.model.request.Request;
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
public class LawyerService {


    @Autowired
    private LawyerRepo lawyerRepo;

    @Autowired
    private AdviceTypeService adviceTypeService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SmsService smsService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private AcceptRequestService arService;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private CallService callService;

    @Autowired
    private OrganizationService orgService;


    public Optional<Lawyer> findLawyerUidAndActive(String userid, boolean active) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUidAndActive(UUID.fromString(userid), active);
        if (lawyer.isPresent())
            return Optional.ofNullable(lawyer.get());
        else
            return Optional.empty();
    }


    public Optional<Lawyer> findByUid(String uid) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(uid));
        if (lawyer.isPresent())
            return Optional.ofNullable(lawyer.get());
        else
            return Optional.empty();
    }

    public Optional<LawyerDTO> findLawyerDTOByUid(int queryType, String uid_userName_Mobile) {
        Optional<Lawyer> lawyer = Optional.empty();
        switch (queryType) {
            case 1:
                lawyer = lawyerRepo.findByUid(UUID.fromString(uid_userName_Mobile));
                break;
            case 2:
                lawyer = lawyerRepo.findByUsername(uid_userName_Mobile);
                break;
            case 3:
                System.out.println("Log---findLawyerDTOByUid--------------------:" + uid_userName_Mobile);
                lawyer = lawyerRepo.findByMobileNumber(Long.parseLong(uid_userName_Mobile));
                break;
        }
        if (lawyer.isPresent()) {
            LawyerDTO lawyerDTO = new LawyerDTO();
            lawyerDTO.setStatus(HttpStatus.OK.value());
            lawyerDTO.setMessage(Constants.KEY_SUCESSE);
            lawyerDTO.setLawyerId(lawyer.get().getUid().toString());
            lawyerDTO.setFirstName(lawyer.get().getFirstName());
            lawyerDTO.setLastName(lawyer.get().getLastName());
            lawyerDTO.setUsername(lawyer.get().getUsername());
            lawyerDTO.setPassword(lawyer.get().getPassword());
            lawyerDTO.setNationalId(lawyer.get().getNationalId());
            lawyerDTO.setBirthDate(lawyer.get().getBirthDate());
            lawyerDTO.setOnline(lawyer.get().getOnline());
            lawyerDTO.setScore(lawyer.get().getScore());
            lawyerDTO.setAvatarHashcode(lawyer.get().getAvatarHashcode());
            lawyerDTO.setActive(lawyer.get().getActive());
            lawyerDTO.setMobileNumber(lawyer.get().getMobileNumber());
//            lawyerDTO.setVerificationCode(lawyer.get().getVerificationCode());
            lawyerDTO.setCreationDate(lawyer.get().getCreationDate());
//            lawyerDTO.setModificationDate(lawyer.get().getModificationDate());
            lawyerDTO.setAvailable(lawyer.get().getAvailable());
            lawyerDTO.setLevel(lawyer.get().getLevel());
            lawyerDTO.setPricePerMinute(lawyer.get().getPricePerMinute());
            lawyerDTO.setVerified(lawyer.get().getVerified());
            List<ExpertiseDTO> list = new ArrayList<>();
            lawyer.get()
                    .getExpertises()
                    .stream()
                    .forEach(ex -> list.add(new ExpertiseDTO(ex.getUid().toString(), ex.getName(), ex.getDescription())));
            lawyerDTO.setExpertiseList(list);
            lawyerDTO.setOrganizationId(lawyer.get().getNationalId());
            lawyerDTO.setAdvicetypeId(lawyer.get().getAdvicetype().getUid().toString());
            return Optional.ofNullable(lawyerDTO);
        }
        return Optional.empty();
    }

    public Optional<ListLawyerDTO> findListLawyerDTO(int queryType, String level_online_active_available_verified, boolean isBoolean) {
        Optional<List<Lawyer>> list = Optional.empty();
        switch (queryType) {
            case 1:
                list = lawyerRepo.findAllByLevel(Short.valueOf(level_online_active_available_verified));
                break;
            case 2:
                list = lawyerRepo.findAllByOnline(isBoolean);
                break;
            case 3:
                list = lawyerRepo.findAllByActive(isBoolean);
                break;
            case 4:
                list = lawyerRepo.findAllByAvailable(isBoolean);
                break;
            case 5:
                list = lawyerRepo.findAllByVerified(isBoolean);
                break;
        }
        if (list.isPresent()) {
            ListLawyerDTO llDTO = new ListLawyerDTO();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(Constants.KEY_SUCESSE);

            List<LawyerDTO> dtoList = new ArrayList<>();
            for (Lawyer lawyer : list.get()) {
                LawyerDTO lawyerDTO = new LawyerDTO();
                lawyerDTO.setLawyerId(lawyer.getUid().toString());
                lawyerDTO.setFirstName(lawyer.getFirstName());
                lawyerDTO.setLastName(lawyer.getLastName());
                lawyerDTO.setUsername(lawyer.getUsername());
                lawyerDTO.setPassword(lawyer.getPassword());
                lawyerDTO.setNationalId(lawyer.getNationalId());
                lawyerDTO.setBirthDate(lawyer.getBirthDate());
                lawyerDTO.setOnline(lawyer.getOnline());
                lawyerDTO.setScore(lawyer.getScore());
                lawyerDTO.setAvatarHashcode(lawyer.getAvatarHashcode());
                lawyerDTO.setActive(lawyer.getActive());
                lawyerDTO.setMobileNumber(lawyer.getMobileNumber());
//            lawyerDTO.setVerificationCode(lawyer.getVerificationCode());
                lawyerDTO.setCreationDate(lawyer.getCreationDate());
//            lawyerDTO.setModificationDate(lawyer.getModificationDate());
                lawyerDTO.setAvailable(lawyer.getAvailable());
                lawyerDTO.setLevel(lawyer.getLevel());
                lawyerDTO.setPricePerMinute(lawyer.getPricePerMinute());
                lawyerDTO.setVerified(lawyer.getVerified());
                List<ExpertiseDTO> expertelist = new ArrayList<>();
                lawyer.getExpertises().stream()
                        .forEach(ex -> expertelist.add(new ExpertiseDTO(ex.getUid().toString(), ex.getName(), ex.getDescription())));
                lawyerDTO.setExpertiseList(expertelist);
                lawyerDTO.setOrganizationId(lawyer.getNationalId());
                lawyerDTO.setAdvicetypeId(lawyer.getAdvicetype().getUid().toString());
                dtoList.add(lawyerDTO);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }

        return Optional.empty();
    }

    public boolean setOnline(String lawyerUid, boolean isOnline) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(lawyerUid));
        if (lawyer.isPresent()) {
            lawyer.get().setOnline(isOnline);
            lawyerRepo.save(lawyer.get());
            return true;
        }
        return false;
    }

    public boolean updateLawyer(LawyerProfileForm lpForm) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(lpForm.getLawyerId()));
        Optional<AdviceType> adviceType = adviceTypeService.findAdviceTypeByUid(lpForm.getAdvicetypeId());
        if (lawyer.isPresent() && adviceType.isPresent()) {

            lawyer.get().setFirstName(lawyer.get().getFirstName());
            lawyer.get().setLastName(lawyer.get().getLastName());
            lawyer.get().setUsername(lawyer.get().getUsername());
            lawyer.get().setPassword(lawyer.get().getPassword());
            lawyer.get().setNationalId(lawyer.get().getNationalId());
            lawyer.get().setBirthDate(lawyer.get().getBirthDate());
            lawyer.get().setScore(lawyer.get().getScore());
            lawyer.get().setAvatarHashcode(lawyer.get().getAvatarHashcode());
            lawyer.get().setMobileNumber(lawyer.get().getMobileNumber());
            lawyer.get().setCreationDate(lawyer.get().getCreationDate());
            lawyer.get().setLevel(lawyer.get().getLevel());
            lawyer.get().setPricePerMinute(lawyer.get().getPricePerMinute());
            lawyer.get().setAdvicetype(adviceType.get());
            lawyerRepo.save(lawyer.get());
            return true;
        }
        return false;
    }

    public boolean updateLawyer(Lawyer lawyer) {
        try{
            lawyerRepo.save(lawyer);
            return true;
        }catch (Exception s){}
        return false;
    }

    public void deleteLawyer(String mobilenumber) {
        Optional<Lawyer> lawyer = lawyerRepo.findByMobileNumber(Long.parseLong(mobilenumber));
        if (lawyer.isPresent())
            lawyerRepo.delete(lawyer.get());
    }

    public void addScore(String lawyerUid, int score) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(lawyerUid));
        if (lawyer.isPresent()) {
            int totalScore = lawyer.get().getScore();
            totalScore += score;
            lawyer.get().setScore(totalScore);
            lawyerRepo.save(lawyer.get());
        }
    }

    public Optional<UUID> registerUser(String phoneNumber, int adviceName) {

        Set<Role> roles = new HashSet<>();
        Role lawyerRole = roleRepo.findByName(RoleName.ROLE_LAWYER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(lawyerRole);
        return saveLawyer(phoneNumber, adviceName, roles);
    }

    private Optional<UUID> saveLawyer(String phoneNumber, int advicetype, Set<Role> roles) {
        Lawyer lawyer = new Lawyer();
        UUID uuid = UUID.randomUUID();
        lawyer.setMobileNumber(Long.valueOf(phoneNumber));
        String code = DataUtil.genarateRandomNumber();
        lawyer.setVerificationCode(code);
        lawyer.setUid(uuid);

        Optional<Organization> orgMaster = orgService.findByName(Constants.KEY_MOSTASHAR);
        if (orgMaster.isPresent())
            lawyer.setOrganization(orgMaster.get());

        lawyer.setDefaultPercentStock(70);

        Optional<AdviceType> adviceType = Optional.empty();
        switch (advicetype) {
            case 1:
                adviceType = adviceTypeService.findAdviceTypeByName("روانشناسی");
                if (adviceType.isPresent()) {
                    lawyer.setAdvicetype(adviceType.get());
                }
                break;
            case 2:
                adviceType = adviceTypeService.findAdviceTypeByName("حقوق");
                if (adviceType.isPresent()) {
                    lawyer.setAdvicetype(adviceType.get());
                }
                break;
        }


        String user = DataUtil.generateAlphaNumericRandomUserPass(8);
        lawyer.setUsername(user);
        lawyer.setPassword(encoder.encode("1"));

        lawyer.setRoles(roles);
        Lawyer userSave = lawyerRepo.save(lawyer);

        if (userSave != null) {
            smsService.sendSms(1, phoneNumber, code);
            return Optional.of(uuid);
        } else
            return Optional.empty();
    }

    public boolean activateUser(boolean isactive, UUID userid) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUid(userid);
        if (lawyer.isPresent()) {
            lawyer.get().setActive(isactive);
            lawyer.get().setVerificationCode("-1");
            lawyerRepo.save(lawyer.get());
            return true;
        }
        return false;
    }

    public UUID createWallletUser(User user) {
        Optional<Wallet> wallet = walletService.findByUserId(user.getUid().toString());
        Optional<Organization> org = orgService.findByName(Constants.KEY_MOSTASHAR);
        UUID uuid;
        if (!wallet.isPresent()) {
            Wallet newWallet = new Wallet();
            uuid = UUID.randomUUID();
            newWallet.setUid(uuid);
            newWallet.setValue(0);
            newWallet.setUser(user);
            if (org.isPresent()) {
                newWallet.setOrganization(org.get());
                org.get().setWallet(newWallet);
            }
            walletService.saveWallet(newWallet);
            return uuid;
        } else {
            return wallet.get().getUid();
        }
    }

    public Optional<Lawyer> findByUserUidAndCode(String uid, String code) {
        System.out.println("Log----------findByUserUidAndCode " + uid + "  " + code);
        Optional<Lawyer> lawyer = lawyerRepo.findByUidAndVerificationCode(UUID.fromString(uid), code);
        if (lawyer.isPresent())
            return Optional.ofNullable(lawyer.get());
        else
            return Optional.empty();
    }

    public Optional<Lawyer> findByMobileNumber(String mobileNumber) {
        Optional<Lawyer> lawyer = lawyerRepo.findByMobileNumber(Long.valueOf(mobileNumber));
        if (lawyer.isPresent())
            return Optional.ofNullable(lawyer.get());
        else
            return Optional.empty();
    }

    //19b2b895-709b-4354-b102-a7fdf0997e6f
    //19b2b895-709b-4354-b102-a7fdf0997e6f
    public void updateCodeCerify(String mobileNumber, String code) {
        Optional<Lawyer> lawyer = lawyerRepo.findByMobileNumber(Long.parseLong(mobileNumber));
        if (lawyer.isPresent()) {
            lawyer.get().setVerificationCode(code);
            lawyerRepo.save(lawyer.get());
        }
    }

    public void reSendCode(String mobileNumber) {
        String code = DataUtil.genarateRandomNumber();
        updateCodeCerify(mobileNumber, code);
        smsService.sendSms(2, mobileNumber, code);
    }

    public Optional<ListFileDTO> findAllFileLawyer(String lawyerId) {
        Optional<List<AcceptRequest>> list = arService.findAllByLawyer(lawyerId);
        List<Request> requestList = new ArrayList<>();
        Set<File> fileList = new HashSet<>();
        if (list.isPresent()) {
            //list AcceptRequest isAcceptedByClient lawyer
            for (AcceptRequest ar : list.get()) {
                if (ar.isAcceptedByClient()) {
                    requestList.add(ar.getRequest());
                }
            }
        }
        //list File
        for (Request request : requestList) {
            fileList.add(request.getFile());
        }
        // list request from calls
        Optional<List<Call>> callList = callService.findAllCallByLawyerId(lawyerId);
        if (callList.isPresent()) {
            for (Call call : callList.get()) {
                if (call.getLawyer().getUid().toString().equals(lawyerId)) {
                    requestList.add(call.getRequest());
                }
            }
        }
        //list File
        for (Request request : requestList) {
            fileList.add(request.getFile());
        }

        ListFileDTO listFileDTO = new ListFileDTO();
        listFileDTO.setStatus(HttpStatus.OK.value());
        listFileDTO.setMessage(Constants.KEY_SUCESSE);
        List<FileDTO> dtoList = new ArrayList<>();
        // list file dto
        for (File file : fileList) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFileId(file.getUid().toString());
            fileDTO.setTitle(file.getTitle());
            fileDTO.setFileNumber(file.getFileNumber());
            fileDTO.setDescription(file.getDescription());
            fileDTO.setCreationDate(file.getCreationDate());
//                fileDTO.setModificationDate(file.getModificationDate());
            if (file.getClient() != null)
                fileDTO.setClientId(file.getClient().getUid().toString());

            dtoList.add(fileDTO);
        }
        listFileDTO.setFiles(dtoList);
        if (fileList != null && fileList.size() > 0) {
            return Optional.of(listFileDTO);
        }

        return Optional.empty();
    }

}
