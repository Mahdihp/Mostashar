package ir.mostashar.model.reminder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReminderDTO extends BaseDTO {

    private String id;
    private Boolean read;
    private String userid;
    private String notificationid;

    public ReminderDTO() {
    }

    public ReminderDTO(Integer status, String message) {
        super(status, message);
    }
}
