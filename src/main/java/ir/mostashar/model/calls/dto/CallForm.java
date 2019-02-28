package ir.mostashar.model.calls.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CallForm {

    private String uid;

    private int failedRetriesCount;

    private String callStatus;

    private int callType;

    private Long startTime;

    private Long endTime;

    private Long creationDate;

    @NotBlank
    private String clientUid;

    @NotBlank
    private String lawyerUid;

    @NotBlank
    private String requestUid;

    public CallForm() {
    }
}
