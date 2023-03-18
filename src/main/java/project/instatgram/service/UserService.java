package project.instatgram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.instatgram.entity.User;
import project.instatgram.entity.UserRoleEnum;
import project.instatgram.jwt.JwtUtil;
import project.instatgram.repository.UserRepository;
import project.instatgram.requestdto.LoginRequestDto;
import project.instatgram.requestdto.SignupRequestDto;
import project.instatgram.responsedto.StatusResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);
        String nickname = requestDto.getNickname();
        // 사용자 확인
        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
        if(found.isPresent()){ throw new IllegalArgumentException("등록된 사용자가 존재합니다.");}
        UserRoleEnum role = UserRoleEnum.USER;
        // 관리자 여부 확인
        if(requestDto.isAdmin()){
            if(!requestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 토큰값이 다릅니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        // 유저 생성
        User user = new User(username, encodedPassword, nickname, role);
        userRepository.save(user);
    }

    @Transactional
    public ResponseEntity login(LoginRequestDto requestDto, HttpServletResponse response){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new IllegalArgumentException("사용자가 존재하지 않습니다."));
        // 저장된 암호와 입력왼 암호 비교
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "댓글 삭제 성공!");
        // Jwt 토큰 발급
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }
}
