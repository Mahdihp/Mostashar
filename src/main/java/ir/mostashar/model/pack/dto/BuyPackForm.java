package ir.mostashar.model.pack.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BuyPackForm {

    private String uid;

    @NotBlank
    private String userUid;

    @NotBlank
    private String name;

    @NotBlank
    private String packUid;

    @NotBlank
    private String requestUid;

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

    private boolean isActive = false;

    @NotBlank
    private String adviceTypeUid;

    @NotBlank
    private String lawyerUid;

    public BuyPackForm() {
    }
}
