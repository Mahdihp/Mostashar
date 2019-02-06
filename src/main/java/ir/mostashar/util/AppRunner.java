package ir.mostashar.util;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.feature.dao.FeatureRepository;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.repository.RoleRepository;
import ir.mostashar.model.user.RoleName;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppRunner implements ApplicationRunner {


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FeatureRepository featureRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initDataBase();
    }


    public void initDataBase() {

        long countAll = clientRepository.count();
        System.out.println("Log----------------Count " + countAll);
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

        Client client2 = new Client();
        client2.setUid(UUID.fromString("b6dc7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        client2.setMobileNumber(9329466051L);
        client2.setTel(9329466051L);
        client2.setAddress("Qom2");
        client2.setVerificationCode("-1");

        Client client3 = new Client();
        client3.setUid(UUID.fromString("b7dc7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        client3.setMobileNumber(9161544275L);
        client3.setTel(9161544275L);
        client3.setAddress("Qom3");
        client3.setVerificationCode("-1");

        Client client4 = new Client();
        client4.setUid(UUID.fromString("b8dc7528-1d44-4ae3-9dc0-c3b8213d45a6"));
        client4.setMobileNumber(9161544277L);
        client4.setTel(9161544277L);
        client4.setAddress("Qom4");
        client4.setVerificationCode("-1");

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

        featureRepository.save(feature1);
        featureRepository.save(feature2);
        featureRepository.save(feature3);
        featureRepository.save(feature4);

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        roleRepository.save(role4);

        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);
        clientRepository.save(client4);
    }


}
