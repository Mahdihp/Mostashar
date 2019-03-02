package ir.mostashar.model.reminder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListReminderDTO extends BaseDTO {

    private List<ReminderDTO> data;

    public ListReminderDTO() {
    }
}
