package ir.mostashar.model.reminder.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReminderForm {

    private String id;
    private Boolean read;

    @NotNull
    private String userUid;

    @NotNull
    private String notificationUid;

    public ReminderForm() {
    }
}
