package ir.mostashar.model.feature.dto;

import lombok.Data;

@Data
public class FeatureForm {

    private String uid;
    private String name;
    private String description;
    private String groupKey;


    public FeatureForm() {
    }
}
