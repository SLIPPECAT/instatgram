package project.instatgram.requestdto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

//    @NotBlank(message = "이메일을 입력해주세요.")
//    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$",
//            message = "이메일 형식으로 작성해주세요.")
    private String username;
//    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-={}\\[\\]\\|:;\"'<>,.?\\/]).{8,15}$",
//            message = "8~15글자, 글자 1개, 숫자 1개, 특수문자 1개 꼭 입력해야합니다.")
//    @NotBlank(message = "비밀번호를 입력해주세요.")
//    @Pattern(regexp = "^([a-zA-Z0-9]+[^A-Za-z0-9_]).{8,15}$",
//            message = "8~15글자, 글자 1개, 숫자 1개, 특수문자 1개 꼭 입력해야합니다.")
    private String password;
    private String nickname;
    private boolean Admin = false;
    private String adminToken= "";
}
