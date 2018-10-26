package ir.mostashar.model.feature.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.feature.dao.FeatureRepository;

@Service
public class FeatureMgr {

	@Autowired
	private FeatureRepository featureRepository;

	public List<Feature> findAll() {
		return featureRepository.findAll();
	}

	public Feature getByName(String name) {
		return featureRepository.findByName(name);
	}

	public Feature save(Feature newFeature) {
		return featureRepository.save(newFeature);
	}

}
