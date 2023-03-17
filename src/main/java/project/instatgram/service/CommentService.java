package project.instatgram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.instatgram.dto.CommentRequestDto;
import project.instatgram.dto.CommentResponseDto;
import project.instatgram.entity.Comment;
import project.instatgram.entity.User;
import project.instatgram.entity.UserRoleEnum;
import project.instatgram.repository.CommentRepository;
import project.instatgram.responsedto.StatusResponseDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Post post = getPost(postId);// Post가 없음 지금..

        Comment comment = new Comment(commentRequestDto, user);
        commentRepository.saveAndFlush(comment);
        post.getComment().add(comment); //Post 엔티티쪽에서 리스트형식으로 받아와야됨 게시글 밑에 달려야되기때문에
        return new CommentResponseDto(comment);
    }

    @Transactional
    public ResponseEntity updateComment(Long commentId, CommentRequestDto commentRequestDto, User user) {
        Comment comment = getComment(commentId);
        if (!(comment.getUser().getId().equals(user.getId()) || user.getRole().equals(UserRoleEnum.ADMIN))) {
            throw new IllegalArgumentException("나중에 커스텀에러코드로 변경");
        }
            comment.updateComment(commentRequestDto);
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "댓글 수정 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }

    public ResponseEntity<?> delete(Long commentId, User user) {
        Comment comment = getComment(commentId);
        if (!(comment.getUser().getId().equals(user.getId()) || user.getRole().equals(UserRoleEnum.ADMIN))) {
            throw new IllegalArgumentException("나중에 커스텀에러코드로 변경");
        }
            commentRepository.deleteById(commentId);
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "댓글 삭제 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }








    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("나중에 커스텀에러코드로 변경")
        );
    }
    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("나중에 커스텀에러코드로 변경")
        );
    }



}
