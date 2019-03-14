package ir.mostashar.model.constant.dto;

import lombok.Data;

@Data
public class ConstantForm {

    private String uid;
    private String key;
    private int value;
    private int type;
    private String description;

    public ConstantForm() {
    }
}
