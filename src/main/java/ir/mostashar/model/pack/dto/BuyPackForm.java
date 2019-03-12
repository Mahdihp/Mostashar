package ir.mostashar.model.pack.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BuyPackForm {

    private String uid;

    @NotBlank
    private String clientId;

    @NotBlank
    private String name;

    @NotBlank
    private String packId;

    @NotBlank
    private String requestId;

    private String description;

    private int  minute;

    private String codeOff; // کد تخفیف

    /**
     * نرخ هر مشاور در هر دقیقه
     */
    @NotBlank
    private int  lawyerPricePerMinute;

    /**
     * قیمت نهایی با اعمال نرخ مشاور روی بسته
     */
    @NotBlank
    private int  totalPrice;


    @NotBlank
    private String adviceTypeId;

    @NotBlank
    private String lawyerId;

    public BuyPackForm() {
    }
}
