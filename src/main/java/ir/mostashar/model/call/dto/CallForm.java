package ir.mostashar.model.call.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "purchaseType:0 or 1 , 1=By Maney Wallet & 1=By Score" +
        "\n" + "packSnapshotId: Price per Minute lawyer")
public class CallForm {

    @NotNull
    private Integer failedRetriesCount;

    private String callStatus;

    private Integer callType;

    private Long startTime;

    private Long endTime;

    @NotNull
    private String clientId;

    @NotNull
    private String lawyerId;

    @NotNull
    private String requestId;

    @NotNull
    private String billId;

    private Integer purchaseType;

    private String packSnapshotId;

    public CallForm() {
    }
}
