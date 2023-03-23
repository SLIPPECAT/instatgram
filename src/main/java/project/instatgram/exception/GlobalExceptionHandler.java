package project.instatgram.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

//    @ExceptionHandler({IllegalArgumentException.class})
//    protected ResponseEntity<Object> illegalArgumentException(IllegalArgumentException e){
//        StatusResponseDto statusResponseDto = new StatusResponseDto();
//        statusResponseDto.setStatus(HttpStatus.BAD_REQUEST.value());
//        statusResponseDto.setMsg(e.getMessage());
//        return new ResponseEntity<>(statusResponseDto, HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler({JwtUnvalidException.class})
//    protected ResponseEntity<Object> JwtUnvalidException(JwtUnvalidException e){
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//    }

//    @ExceptionHandler({SignatureException.})

//    @ExceptionHandler({Exception.class})
//    protected ResponseEntity<Object> handleServerException(Exception e) {
//        return new ResponseEntity<>(new ErrorDto(TOKEN_SECURITY_ERROR.getStatus(), TOKEN_SECURITY_ERROR.getMsg()), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}