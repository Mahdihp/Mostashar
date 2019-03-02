package ir.mostashar.model.role.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import ir.mostashar.model.role.RoleName;
import ir.mostashar.model.role.dto.RoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.feature.repository.FeatureRepo;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.repository.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private FeatureRepo featureRepo;

	public boolean createRole(RoleForm role) {

		Role role1 = new Role();
		role1.setUid(UUID.randomUUID());
		role1.setName(RoleName.valueOf(role.getRoleName()));
		role1.setUserDefined(true);
		role1.setDescription(role.getDescription());


		/*Set<Feature> features = role.getFeatures();
		Set<Feature> featuresSet = new HashSet<Feature>();
		if (features != null && !features.isEmpty()) {
			for (Feature feature : features) {

				Example<Feature> example = Example.of(feature);
				Optional<Feature> savedFeature = featureRepo.findOne(example);
				if (savedFeature != null && savedFeature.isPresent()) {
					featuresSet.add(savedFeature.get());
				} else {
					feature.setId(UUID.randomUUID());
					featuresSet.add(featureRepo.save(feature));
				}
			}
			role.setFeatures(featuresSet);
		}

		role.setId(UUID.randomUUID());
*/
		return false;
	}

	public List<Role> findAll() {
		return roleRepo.findAll();
	}

	public Role getByName(String name) {
		return null;//roleRepository.findByName(name);
	}

}
