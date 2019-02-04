package ir.mostashar.util;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AppRunner implements ApplicationRunner {


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserRepository userRepository;

    public AppRunner() {

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initDataBase();
    }


    public void initDataBase() {

//		User user=new User();
//		user.setUid(UUID.randomUUID());
//		user.setMobileNumber(9339466051L);
//		userRepository.save(user);
//        List<User> all = userRepository.findAll();
//        System.out.println(all.get(0));
//        System.out.println(all.get(1));
//		User newUser=new User(UUID.randomUUID(),9339466060L);
//		userRepository.save(newUser);
		/*User user = userMgr.findByUsername("admin");
		if (user != null)
			return;

		// Features
		Set<Feature> features = new HashSet<Feature>();
		for (String featureName : FeatureNames.getAllFeatureNames()) {
			Feature feature = featureMgr.getByName(featureName);
			if (feature != null) {
				features.add(feature);
			} else {
				Feature newFeature = new Feature();
				newFeature.setName(featureName);
				newFeature.setDescription(featureName);
				newFeature.setUid(UUID.randomUUID());
				features.add(newFeature);
			}
		}

		// Admin Role
		Set<Role> roles = new HashSet<Role>();
		Role role = roleMgr.getByName("ADMIN");
		if (role != null) {
			roles.add(role);
		} else {
			Role newRole = new Role();
			newRole.setName("ADMIN");
			newRole.setDescription("admin role");
			newRole.setUid(UUID.randomUUID());
			newRole.setFeatures(features);
			roles.add(newRole);
		}

		// admin User
		User adminUser = new User();
		adminUser.setFirstName("admin");
		adminUser.setLastName("admin");
		adminUser.setPassword("admin");
		adminUser.setUsername("admin");
		adminUser.setMobileNumber("09135189683");
		adminUser.setUid(UUID.randomUUID());
		adminUser.setRoles(roles);
		userMgr.save(adminUser);*/

    }


}
