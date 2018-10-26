package ir.mostashar.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchQuery {

	@JsonProperty
	private String cityName;

	@JsonProperty
	private String stateName;

	@JsonProperty
	private Integer rateInCountryStart = -1;

	@JsonProperty
	private Integer rateInCountryEnd = -1;

	@JsonProperty
	private Integer rateInStateStart = -1;

	@JsonProperty
	private Integer rateInStateEnd = -1;

	@JsonProperty
	private Integer changeRateStart = -1;

	@JsonProperty
	private Integer changeRateEnd = -1;

	@JsonProperty
	private Integer populationCountStart = -1;

	@JsonProperty
	private Integer populationCountEnd = -1;

	@JsonProperty
	private Integer populationYearStart = -1;

	@JsonProperty
	private Integer populationYearEnd = -1;

	public String getCityName() {
		return cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public Integer getRateInCountryStart() {
		return rateInCountryStart;
	}

	public Integer getRateInCountryEnd() {
		return rateInCountryEnd;
	}

	public Integer getRateInStateStart() {
		return rateInStateStart;
	}

	public Integer getRateInStateEnd() {
		return rateInStateEnd;
	}

	public Integer getChangeRateStart() {
		return changeRateStart;
	}

	public Integer getChangeRateEnd() {
		return changeRateEnd;
	}

	public Integer getPopulationCountStart() {
		return populationCountStart;
	}

	public Integer getPopulationCountEnd() {
		return populationCountEnd;
	}

	public Integer getPopulationYearStart() {
		return populationYearStart;
	}

	public Integer getPopulationYearEnd() {
		return populationYearEnd;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public void setRateInCountryStart(Integer rateInCountryStart) {
		this.rateInCountryStart = rateInCountryStart;
	}

	public void setRateInCountryEnd(Integer rateInCountryEnd) {
		this.rateInCountryEnd = rateInCountryEnd;
	}

	public void setRateInStateStart(Integer rateInStateStart) {
		this.rateInStateStart = rateInStateStart;
	}

	public void setRateInStateEnd(Integer rateInStateEnd) {
		this.rateInStateEnd = rateInStateEnd;
	}

	public void setChangeRateStart(Integer changeRateStart) {
		this.changeRateStart = changeRateStart;
	}

	public void setChangeRateEnd(Integer changeRateEnd) {
		this.changeRateEnd = changeRateEnd;
	}

	public void setPopulationCountStart(Integer populationCountStart) {
		this.populationCountStart = populationCountStart;
	}

	public void setPopulationCountEnd(Integer populationCountEnd) {
		this.populationCountEnd = populationCountEnd;
	}

	public void setPopulationYearStart(Integer populationYearStart) {
		this.populationYearStart = populationYearStart;
	}

	public void setPopulationYearEnd(Integer populationYearEnd) {
		this.populationYearEnd = populationYearEnd;
	}

	public Boolean isNull() {

		if (cityName == null && rateInCountryStart == null && rateInCountryEnd == null && rateInStateStart == null
				&& rateInStateEnd == null && stateName == null && changeRateStart == null && changeRateEnd == null
				&& populationCountStart == null && populationCountEnd == null && populationYearStart == null
				&& populationYearEnd == null) {
			return true;
		}
		return false;
	}

}
