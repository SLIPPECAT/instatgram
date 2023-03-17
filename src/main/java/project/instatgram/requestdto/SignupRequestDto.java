package project.instatgram.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    private String username;
    private String password;
    private String nickname;
    private boolean Admin = false;
    private String adminToken= "";

}
