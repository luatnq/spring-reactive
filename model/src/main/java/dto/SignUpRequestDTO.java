package dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequestDTO {
    @NotNull
    @Size(min = 6, max = 20, message = "Username is from 6 to 20 characters!")
    private String username;

    @JsonProperty("email")
    @Size(min = 6, max = 30, message = "Wrong format email!")
    private String email;

    @JsonProperty("phone_number")
    @Size(min = 10, max = 10, message = "Wrong format phone number!")
    private String phoneNumber;

    @NotBlank
    @Size(min = 6,max = 50, message = "Password is from 6 to 50 characters!")
    private String password;

    @JsonProperty("full_name")
    private String fullName;

}
