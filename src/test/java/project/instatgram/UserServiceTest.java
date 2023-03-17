package project.instatgram;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import project.instatgram.entity.User;
import project.instatgram.entity.UserRoleEnum;
import project.instatgram.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

    UserRepository userRepository;

    @Test
    @DisplayName("회원가입 기능 테스트")
    public void signup(){
        // given
        String username = "아이유";
        String password = "너랑나";
        String nickname = "이지은";
        // when
        User user = new User(username, password, nickname, UserRoleEnum.USER);
        userRepository.save(user);
        // then

    }


}
