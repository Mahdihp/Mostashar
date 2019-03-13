package ir.mostashar.model.call.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CallForm {

//    private String callId;

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

    public CallForm() {
    }
}
