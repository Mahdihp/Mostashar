package ir.mostashar.utils;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.repository.AdviceTypeRepo;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.repository.ClientRepo;
import ir.mostashar.model.constant.Constant;
import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.feature.repository.FeatureRepo;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.repository.FileRepo;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepo;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.pack.repository.PackRepo;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.repository.RoleRepo;
import ir.mostashar.model.role.RoleName;
import ir.mostashar.model.wallet.Wallet;
import ir.mostashar.model.wallet.repository.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    LawyerRepo lawyerRepo;

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    FeatureRepo featureRepo;

    @Autowired
    FileRepo fileRepo;

    @Autowired
    AdviceTypeRepo adviceTypeRepo;

    @Autowired
    PackRepo packRepo;

    @Autowired
    WalletRepo walletRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initDataBase();
    }


    public void initDataBase() {

        long countAll = clientRepo.count();
        System.out.println("Log---------------- Client Count " + countAll);
        if (countAll <= 0)
            insertToDb();
    }

    private void insertToDb() {


        Client client1 = new Client();
        client1.setUid(UUID.fromString("b5dc7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        client1.setMobileNumber(9134528901L);
        client1.setTel(9134528901L);
        client1.setAddress("Qom1");
        client1.setVerificationCode("-1");
        client1.setActive(true);

        Client client2 = new Client();
        client2.setUid(UUID.fromString("b6dc7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        client2.setMobileNumber(9329466051L);
        client2.setTel(9329466051L);
        client2.setAddress("Qom2");
        client2.setVerificationCode("-1");
        client2.setActive(true);

        Client client3 = new Client();
        client3.setUid(UUID.fromString("b7dc7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        client3.setMobileNumber(9161544275L);
        client3.setTel(9161544275L);
        client3.setAddress("Qom3");
        client3.setVerificationCode("-1");
        client3.setActive(true);

        Client client4 = new Client();
        client4.setUid(UUID.fromString("b8dc7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        client4.setMobileNumber(9161544277L);
        client4.setTel(9161544277L);
        client4.setAddress("Qom4");
        client4.setVerificationCode("-1");
        client4.setActive(true);

        Feature feature1 = new Feature();
        feature1.setUid(UUID.fromString("e6f9b9bd-9396-4b78-a5e8-569ff236d991"));
        feature1.setDescription("ACCESS_TO_UPDATE_ONE");
        feature1.setName("ACCESS_TO_UPDATE_ONE");

        Feature feature2 = new Feature();
        feature2.setUid(UUID.fromString("e1f9b9bd-9396-4b78-a5e8-569ff236d991"));
        feature2.setDescription("ACCESS_TO_UPDATE_ONE");
        feature2.setName("ACCESS_TO_UPDATE_ONE");

        Feature feature3 = new Feature();
        feature3.setUid(UUID.fromString("e2f9b9bd-9396-4b78-a5e8-569ff236d991"));
        feature3.setDescription("ACCESS_TO_UPDATE_ONE");
        feature3.setName("ACCESS_TO_UPDATE_ONE");

        Feature feature4 = new Feature();
        feature4.setUid(UUID.fromString("e3f9b9bd-9396-4b78-a5e8-569ff236d991"));
        feature4.setDescription("ACCESS_TO_UPDATE_ONE");
        feature4.setName("ACCESS_TO_UPDATE_ONE");

        Role role1 = new Role();
        role1.setUid(UUID.fromString("b4482c30-b3a5-4401-a274-016bda28fdce"));
        role1.setName(RoleName.ROLE_ADMIN);
        role1.setUserDefined(true);
        role1.setDescription("admin");

        Role role2 = new Role();
        role2.setUid(UUID.fromString("b4482c31-b3a5-4401-a274-016bda28fdce"));
        role2.setName(RoleName.ROLE_RESELLER);
        role2.setUserDefined(true);
        role2.setDescription("reseller");

        Role role3 = new Role();
        role3.setUid(UUID.fromString("b4482c32-b3a5-4401-a274-016bda28fdce"));
        role3.setName(RoleName.ROLE_CLIENT);
        role3.setUserDefined(true);
        role3.setDescription("client");

        Role role4 = new Role();
        role4.setUid(UUID.fromString("b4482c33-b3a5-4401-a274-016bda28fdce"));
        role4.setName(RoleName.ROLE_LAWYER);
        role4.setUserDefined(true);
        role4.setDescription("lawyer");

        Set<Feature> features1 = new HashSet<>();
        Set<Feature> features2 = new HashSet<>();
        Set<Feature> features3 = new HashSet<>();
        Set<Feature> features4 = new HashSet<>();

        features1.add(feature1);
        features1.add(feature2);
        features1.add(feature3);
        features1.add(feature4);

        features2.add(feature2);
        features2.add(feature3);
        features2.add(feature4);

        features3.add(feature3);
        features3.add(feature4);

        features4.add(feature3);
        features4.add(feature4);


        Set<Role> roles1 = new HashSet<>();
        Set<Role> roles2 = new HashSet<>();
        Set<Role> roles3 = new HashSet<>();
        Set<Role> roles4 = new HashSet<>();

        roles1.add(role1);
        roles1.add(role2);
        roles1.add(role3);
        roles1.add(role4);

        roles2.add(role1);
        roles2.add(role2);
        roles2.add(role3);
        roles2.add(role4);

        roles3.add(role3);
        roles3.add(role4);

        roles4.add(role3);
        roles4.add(role4);

        role1.setFeatures(features1);
        role2.setFeatures(features2);
        role3.setFeatures(features3);
        role4.setFeatures(features4);

        client1.setRoles(roles1);
        client2.setRoles(roles2);
        client3.setRoles(roles3);
        client4.setRoles(roles4);


        File file1 = new File();
        file1.setUid(UUID.fromString("b4482c10-b3a5-4401-a274-016bda28fdce"));
        file1.setTitle("Test File 1");
        file1.setDescription("Description Test 1");
        file1.setClient(client1);
        file1.setCreationDate(System.currentTimeMillis());

        File file2 = new File();
        file2.setUid(UUID.fromString("b4482c11-b3a5-4401-a274-016bda28fdce"));
        file2.setTitle("Test File 2");
        file2.setDescription("Description Test 2");
        file2.setClient(client2);
        file2.setCreationDate(System.currentTimeMillis());

        File file3 = new File();
        file3.setUid(UUID.fromString("b4482c12-b3a5-4401-a274-016bda28fdce"));
        file3.setTitle("Test File 3");
        file3.setDescription("Description Test 3");
        file3.setClient(client3);
        file3.setCreationDate(System.currentTimeMillis());

        // 1 = روانشناسی
        // 2 = حقوق
        AdviceType adviceType1 = new AdviceType();
        adviceType1.setUid(UUID.fromString("11482c33-b3a5-4401-a274-016bda28fdce"));
        adviceType1.setName("روانشناسی");
        adviceType1.setType((short) 1);
        adviceType1.setDescription("مشاوره روانشناسی");

        AdviceType adviceType2 = new AdviceType();
        adviceType2.setUid(UUID.fromString("12482c33-b3a5-4401-a274-016bda28fdce"));
        adviceType1.setName("حقوق");
        adviceType1.setType((short) 2);
        adviceType1.setDescription("مشاوره حقوقی");


        Pack pack1 = new Pack();
        pack1.setUid(UUID.fromString("13482c33-b3a5-4401-a274-016bda28fdce"));
        pack1.setMinute(0);
        pack1.setName("5 دقیقه مشاوره");
        pack1.setAdvicetype(adviceType2);
        pack1.setActive(true);

        Pack pack2 = new Pack();
        pack2.setUid(UUID.fromString("14482c33-b3a5-4401-a274-016bda28fdce"));
        pack2.setMinute(10);
        pack2.setName("10 دقیقه مشاوره");
        pack2.setAdvicetype(adviceType2);
        pack2.setActive(true);

        Pack pack3 = new Pack();
        pack3.setUid(UUID.fromString("15482c33-b3a5-4401-a274-016bda28fdce"));
        pack3.setMinute(15);
        pack3.setName("15 دقیقه مشاوره");
        pack3.setAdvicetype(adviceType2);
        pack3.setActive(true);

        Pack pack4 = new Pack();
        pack4.setUid(UUID.fromString("16482c33-b3a5-4401-a274-016bda28fdce"));
        pack4.setMinute(30);
        pack4.setName("30 دقیقه مشاوره");
        pack4.setAdvicetype(adviceType2);
        pack4.setActive(true);

        Pack pack5 = new Pack();
        pack5.setUid(UUID.fromString("17482c33-b3a5-4401-a274-016bda28fdce"));
        pack5.setMinute(45);
        pack5.setName("45 دقیقه مشاوره");
        pack5.setAdvicetype(adviceType2);
        pack5.setActive(true);

        Lawyer lawyer1 = new Lawyer();
        lawyer1.setUid(UUID.fromString("110c7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        lawyer1.setLevel(1); // 3 level Lawyer
        lawyer1.setAdvicetype(adviceType1);
        lawyer1.setAvailable(true);
        lawyer1.setVerified(true);
        lawyer1.setMobileNumber(9144528901L);
        lawyer1.setVerificationCode("-1");
        lawyer1.setPricePerMinute(3000);

        Lawyer lawyer2 = new Lawyer();
        lawyer2.setUid(UUID.fromString("111c7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        lawyer2.setLevel(2); // 3 level Lawyer
        lawyer2.setAdvicetype(adviceType1);
        lawyer2.setAvailable(true);
        lawyer2.setVerified(true);
        lawyer2.setMobileNumber(9154528901L);
        lawyer2.setVerificationCode("-1");
        lawyer2.setPricePerMinute(6000);

        Lawyer lawyer3 = new Lawyer();
        lawyer3.setUid(UUID.fromString("112c7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        lawyer3.setLevel(3); // 3 level Lawyer
        lawyer3.setAdvicetype(adviceType1);
        lawyer3.setAvailable(true);
        lawyer3.setVerified(true);
        lawyer3.setMobileNumber(9164528901L);
        lawyer3.setVerificationCode("-1");
        lawyer3.setPricePerMinute(9000);


        Wallet wallet1 = new Wallet();
        wallet1.setUid(UUID.fromString("113c7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        wallet1.setValue(0);
        wallet1.setUser(client1);

        Wallet wallet2 = new Wallet();
        wallet2.setUid(UUID.fromString("114c7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        wallet2.setValue(0);
        wallet2.setUser(client2);

        Wallet wallet3 = new Wallet();
        wallet3.setUid(UUID.fromString("115c7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        wallet3.setValue(0);
        wallet3.setUser(client3);

        Wallet wallet4 = new Wallet();
        wallet4.setUid(UUID.fromString("116c7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        wallet4.setValue(0);
        wallet4.setUser(client4);

        Wallet wallet5 = new Wallet();
        wallet5.setUid(UUID.fromString("117c7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        wallet5.setValue(0);
        wallet5.setUser(lawyer1);

        Wallet wallet6 = new Wallet();
        wallet6.setUid(UUID.fromString("118c7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        wallet6.setValue(0);
        wallet6.setUser(lawyer2);

        Wallet wallet7 = new Wallet();
        wallet7.setUid(UUID.fromString("119c7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        wallet7.setValue(0);
        wallet7.setUser(lawyer3);

        Constant constant1 = new Constant();
        constant1.setUid(UUID.fromString("120c7528-1d44-4ae3-9dc0-c3b8213d45a6"));

        Constant constant2 = new Constant();
        constant2.setUid(UUID.fromString("121c7528-1d44-4ae3-9dc0-c3b8213d45a6"));

        Constant constant3 = new Constant();
        constant3.setUid(UUID.fromString("122c7528-1d44-4ae3-9dc0-c3b8213d45a6"));


        featureRepo.save(feature1);
        featureRepo.save(feature2);
        featureRepo.save(feature3);
        featureRepo.save(feature4);

        roleRepo.save(role1);
        roleRepo.save(role2);
        roleRepo.save(role3);
        roleRepo.save(role4);

        clientRepo.save(client1);
        clientRepo.save(client2);
        clientRepo.save(client3);
        clientRepo.save(client4);

        fileRepo.save(file1);
        fileRepo.save(file2);
        fileRepo.save(file3);

        adviceTypeRepo.save(adviceType1);
        adviceTypeRepo.save(adviceType2);

        packRepo.save(pack1);
        packRepo.save(pack2);
        packRepo.save(pack3);
        packRepo.save(pack4);
        packRepo.save(pack5);

        lawyerRepo.save(lawyer1);
        lawyerRepo.save(lawyer2);
        lawyerRepo.save(lawyer3);

        walletRepo.save(wallet1);
        walletRepo.save(wallet2);
        walletRepo.save(wallet3);
        walletRepo.save(wallet4);
        walletRepo.save(wallet5);
        walletRepo.save(wallet6);
        walletRepo.save(wallet7);

    }


}
