package project.instatgram.dto;

import lombok.Getter;
import project.instatgram.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String comment;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;



    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.nickname = comment.getUser().getNickname();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();

    }

//    public CommentResponseDto(Comment comment, Long postId) {
//        this.id = comment.getId();
//        this.comment = comment.getComment();
//        this.nickname = comment.getUser().getNickname();
//        this.createdAt = comment.getCreatedAt();
//        this.modifiedAt = comment.getModifiedAt();
//
//    }
}
