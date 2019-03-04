package ir.mostashar.model.reminder.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReminderForm {

    private String id;
    private boolean read = false;

    @NotBlank
    private String userUid;

    @NotBlank
    private String notificationUid;

    public ReminderForm() {
    }
}
