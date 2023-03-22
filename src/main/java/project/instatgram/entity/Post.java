package project.instatgram.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.instatgram.dto.PostRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@RequiredArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "comment_id")
    private List<Comment> comments =new ArrayList<>();


    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.nickname = postRequestDto.getNickname();
        this.user = user;
    }

    public void updatePost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.nickname = postRequestDto.getNickname();
        //
    }
}
