package ir.mostashar.util;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.feature.FeatureNames;
import ir.mostashar.model.feature.logic.FeatureMgr;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.logic.RoleMgr;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.logic.UserMgr;

@Component
public class DataSuit implements ApplicationRunner {

	private UserMgr userMgr;

	private RoleMgr roleMgr;

	private FeatureMgr featureMgr;

	@Autowired
	public DataSuit(UserMgr userMgr, RoleMgr roleMgr, FeatureMgr featureMgr) {

		this.userMgr = userMgr;
		this.roleMgr = roleMgr;
		this.featureMgr = featureMgr;
	}

	public void initalAdminUser() {

		User user = userMgr.findByUsername("admin");
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
		adminUser.setUserName("admin");
		adminUser.setMobileNumber("09135189683");
		adminUser.setUid(UUID.randomUUID());
		adminUser.setRoles(roles);
		userMgr.save(adminUser);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		initalAdminUser();
	}

}
