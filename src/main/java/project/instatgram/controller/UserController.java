package project.instatgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.instatgram.requestdto.LoginRequestDto;
import project.instatgram.requestdto.SignupRequestDto;
import project.instatgram.service.UserService;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequestDto requestDto){
        userService.signup(requestDto);
        String msg = "회원가입이 완료됐습니다.";
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse responseDto){
        userService.login(requestDto, responseDto);
        String msg = "로그인이 완료됐습니다.";
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
