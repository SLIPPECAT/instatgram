package project.instatgram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.instatgram.responsedto.StatusResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<Object> illegalArgumentException(IllegalArgumentException e){

        StatusResponseDto statusResponseDto = new StatusResponseDto();
        statusResponseDto.setStatus(HttpStatus.BAD_REQUEST.value());
        statusResponseDto.setMsg(e.getMessage());
        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);
    }

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