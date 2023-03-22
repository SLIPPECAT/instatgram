package project.instatgram.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignupRequestDto {


    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]+$")
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;


    private boolean Admin = false;
    private String adminToken= "";
}
