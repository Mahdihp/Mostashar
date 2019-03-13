package ir.mostashar.model.pack.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BuyPackForm {

    @NotNull
    private String clientId;

    @NotNull
    private String name;

    @NotNull
    private String packId;

    @NotNull
    private String requestId;

    private String description;

    private Integer minute;

    private String codeOff; // کد تخفیف

    /**
     * نرخ هر مشاور در هر دقیقه
     */
    @NotNull
    private Integer lawyerPricePerMinute;

    /**
     * قیمت نهایی با اعمال نرخ مشاور روی بسته
     */
    @NotNull
    private Integer totalPrice;


    @NotNull
    private String adviceTypeId;

    @NotNull
    private String lawyerId;

    public BuyPackForm() {
    }
}
