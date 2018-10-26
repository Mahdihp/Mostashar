package ir.mostashar.model.feature.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.feature.FeatureNames;
import ir.mostashar.model.feature.dto.FeatureDTO;
import ir.mostashar.model.feature.logic.FeatureMgr;

@RestController
@RequestMapping("/IranPopulation/api/feature")
public class FeatureService {

	@Autowired
	private FeatureMgr featureMgr;

	@GetMapping("/items/getName")
	public List<String> getFeatureNames() {
		return new ArrayList<String>(FeatureNames.getAllFeatureNames());
	}

	@GetMapping("/items")
	public List<FeatureDTO> getFeatures() {

		List<FeatureDTO> result = new ArrayList<FeatureDTO>();
		List<Feature> features = featureMgr.findAll();
		for (Feature feature : features) {
			FeatureDTO featureDto = new FeatureDTO();
			featureDto.loadFrom(feature);
			result.add(featureDto);
		}

		return result;

	}

}
