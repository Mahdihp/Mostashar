package ir.mostashar.model.feature.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import ir.mostashar.model.feature.Feature;

public class FeatureDTO {

	@JsonProperty
	private String uid;

	@JsonProperty
	private String name;

	@JsonProperty
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void loadFrom(Feature feature) {

		this.name = feature.getName();
		this.description = feature.getDescription();
		this.uid = feature.getUid().toString();

	}

	public void saveTo(Feature feature) {

		feature.setName(this.name);
		feature.setDescription(this.description);

	}

}
