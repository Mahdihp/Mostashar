package ir.mostashar.model.role.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.feature.dao.FeatureRepository;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.dao.RoleRepository;

@Service
public class RoleMgr {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private FeatureRepository featureRepository;

	public Role save(Role role) {

		Set<Feature> features = role.getFeatures();
		Set<Feature> featuresSet = new HashSet<Feature>();
		if (features != null && !features.isEmpty()) {
			for (Feature feature : features) {

				Example<Feature> example = Example.of(feature);
				Optional<Feature> savedFeature = featureRepository.findOne(example);
				if (savedFeature != null && savedFeature.isPresent()) {
					featuresSet.add(savedFeature.get());
				} else {
					feature.setUid(UUID.randomUUID());
					featuresSet.add(featureRepository.save(feature));
				}
			}
			role.setFeatures(featuresSet);
		}

		role.setUid(UUID.randomUUID());

		return roleRepository.save(role);
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role getByName(String name) {
		return roleRepository.findByName(name);
	}

}
