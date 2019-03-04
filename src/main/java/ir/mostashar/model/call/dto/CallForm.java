package ir.mostashar.model.call.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CallForm {

    private String callId;

    private int failedRetriesCount;

    private String callStatus;

    private int callType;

    private Long startTime;

    private Long endTime;

    private Long creationDate;

    @NotBlank
    private String clientId;

    @NotBlank
    private String lawyerId;

    @NotBlank
    private String requestId;

    public CallForm() {
    }
}
