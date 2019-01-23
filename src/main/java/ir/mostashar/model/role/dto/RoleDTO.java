package ir.mostashar.model.role.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.feature.dto.FeatureDTO;
import ir.mostashar.model.role.Role;

public class RoleDTO {

	@JsonProperty
	private String uid;

	@JsonProperty
	private String name;

	@JsonProperty
	private String description;

	@JsonProperty
	private List<FeatureDTO> features;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<FeatureDTO> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureDTO> features) {
		this.features = features;
	}

	public void loadFrom(Role role) {
		this.uid = role.getUid().toString();
//		this.name = role.getName();
		this.description = role.getDescription();

		Set<Feature> featuresSet = role.getFeatures();
		List<FeatureDTO> featureNames = new ArrayList<FeatureDTO>();
		if (featuresSet != null && !featuresSet.isEmpty()) {
			for (Feature feature : featuresSet) {

				FeatureDTO featureDto = new FeatureDTO();
				featureDto.loadFrom(feature);
				featureNames.add(featureDto);
			}
		}
		this.features = featureNames;
	}

	public void saveTo(Role role) {
//		role.setName(this.name);
		role.setDescription(this.description);

		Set<Feature> featuresSet = new HashSet<Feature>();
		if (this.features != null && !this.features.isEmpty()) {
			for (FeatureDTO featureDto : this.features) {
				Feature feature = new Feature();
				featureDto.saveTo(feature);
				featuresSet.add(feature);
			}
		}
		role.setFeatures(featuresSet);
	}

}
