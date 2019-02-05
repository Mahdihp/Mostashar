package ir.mostashar.model.client.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignUpForm {

    @NotBlank
    @Size(max = 10)
    private String phoneNumber;

    @NotBlank
    private Set<String> role;

    public SignUpForm() {  }

    public SignUpForm(String phoneNumber, Set<String> role) {
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
