package project.instatgram.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.instatgram.entity.Comment;
import project.instatgram.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private long id;
    private String title;
    private String content;
    private String nickname;

    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    private final List<CommentResponseDto> commentList = new ArrayList<>();

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickname = post.getNickname();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        for(Comment comment : post.getComments()){
            commentList.add(new CommentResponseDto(comment));
        }
    }
}
