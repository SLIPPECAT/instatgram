package project.instatgram.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.instatgram.dto.CommentRequestDto;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;


    public Comment(CommentRequestDto commentRequestDto, User user) {
        this.comment = commentRequestDto.getComment();
        this.nickname = user.getNickname();
        this.user = user;

    }

    public void updateComment(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
