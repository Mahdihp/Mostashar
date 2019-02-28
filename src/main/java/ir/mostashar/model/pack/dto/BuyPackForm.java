package ir.mostashar.model.pack.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BuyPackForm {

    private String uid;

    @NotBlank
    private String userId;

    @NotBlank
    private String name;

    @NotBlank
    private String packId;

    @NotBlank
    private String requestId;

    private String description;

    private int  minute;

    /**
     * نرخ هر مشاور در هر دقیقه
     */
    private int  lawyerPricePerMinute;

    /**
     * قیمت نهایی با اعمال نرخ مشاور روی بسته
     */
    private int  totalPrice;

    private boolean active = false;

    @NotBlank
    private String adviceTypeId;

    @NotBlank
    private String lawyerId;

    public BuyPackForm() {
    }
}
