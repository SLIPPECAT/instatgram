package project.instatgram.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.instatgram.requestdto.SignupRequestDto;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, String nickname, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public User(SignupRequestDto reqDto, UserRoleEnum role) {
        this.username = reqDto.getUsername();
        this.password = reqDto.getPassword();
        this.nickname = reqDto.getNickname();
        this.role = role;
    }
}
