package project.instatgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.instatgram.exception.CustomException;
import project.instatgram.exception.ErrorCode;
import project.instatgram.exception.SuccessCode;
import project.instatgram.requestdto.LoginRequestDto;
import project.instatgram.requestdto.SignupRequestDto;
import project.instatgram.responsedto.StatusResponseDto;
import project.instatgram.service.UserService;

import javax.servlet.http.HttpServletResponse;
@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<StatusResponseDto> signup(@Validated @RequestBody SignupRequestDto requestDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.NOT_FOUND_SIGNUP_USER);
        }
        return userService.signup(requestDto);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody LoginRequestDto requestDto, HttpServletResponse responseDto){
        userService.login(requestDto, responseDto);

        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setStatus(SuccessCode.LOGIN_SUCCESS.getStatus());
        statusResponseDto.setMsg(SuccessCode.LOGIN_SUCCESS.getMsg());

        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);
    }
}
