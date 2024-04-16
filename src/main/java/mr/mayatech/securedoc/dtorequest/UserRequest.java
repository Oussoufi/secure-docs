package mr.mayatech.securedoc.dtorequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    @NotEmpty(message = "First name cannot be empty or null")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty or null")
    private String lastName;
    @NotEmpty(message = "Email name cannot be empty or null")
    private String email;
    @NotEmpty(message = "Password cannot be empty or null")
    private String password;
    @NotEmpty(message = " Bio cannot be empty or null")
    private String bio;
    @NotEmpty(message = "Phone cannot be empty or null")
    private String phone;

}
