package ir.mostashar.model.factordetails.dto;

import lombok.Data;

@Data
public class FactorDetailForm {

    private String factorDetailId;
    private String itemName;
    private int countItem;
    private long pricePer;
    private long totalPrice;
    private String description;
    private String factorId;

    public FactorDetailForm() {
    }
}
