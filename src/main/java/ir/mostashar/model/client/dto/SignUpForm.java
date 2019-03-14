package ir.mostashar.model.client.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SignUpForm {

    @NotNull
    @Size(min = 11,max = 11) //09339466051
    private String phoneNumber;


    public SignUpForm() {  }

    public SignUpForm(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
