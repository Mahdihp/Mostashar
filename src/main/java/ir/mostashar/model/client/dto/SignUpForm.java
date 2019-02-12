package ir.mostashar.model.client.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpForm {

    @NotBlank
    @Size(min = 11,max = 11) //09339466051
    private String phoneNumber;


    public SignUpForm() {  }

    public SignUpForm(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
