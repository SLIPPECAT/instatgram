package project.instatgram.responsedto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatusResponseDto {

    private int status;
    private String msg;

    public StatusResponseDto(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
