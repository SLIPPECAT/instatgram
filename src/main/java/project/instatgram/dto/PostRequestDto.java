package project.instatgram.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private String nickname;

    public PostRequestDto(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.nickname = postRequestDto.getNickname();
    }
}
