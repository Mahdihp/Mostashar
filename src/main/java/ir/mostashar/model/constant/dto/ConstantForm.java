package ir.mostashar.model.constant.dto;

import lombok.Data;

@Data
public class ConstantForm {

    private String uid;
    private String key;
    private String value;
    private String type;
    private String description;

    public ConstantForm() {
    }
}
