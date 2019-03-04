package ir.mostashar.model.notification.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListNotificationDTO extends BaseDTO {

    private List<NotificationDTO> data;

    public ListNotificationDTO() {
    }
}
