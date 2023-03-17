package project.instatgram.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SecurityExceptionDto {

    private int status;
    private String msg;

    public SecurityExceptionDto(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}