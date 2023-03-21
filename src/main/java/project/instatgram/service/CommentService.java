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
import project.instatgram.entity.Post;
import project.instatgram.entity.User;
import project.instatgram.entity.UserRoleEnum;
import project.instatgram.repository.CommentRepository;
import project.instatgram.repository.PostRepository;
import project.instatgram.responsedto.StatusResponseDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Post post = getPost(postId);

        Comment comment = new Comment(commentRequestDto, user);
        commentRepository.saveAndFlush(comment);
        post.getComments().add(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public ResponseEntity<?> updateComment(Long commentId, CommentRequestDto commentRequestDto, User user) {
        Comment comment = getComment(commentId);
        if (!(comment.getUser().getId().equals(user.getId()) || user.getRole().equals(UserRoleEnum.ADMIN))) {
            throw new IllegalArgumentException("댓글을 수정할 수 없습니다.");
        }
        comment.updateComment(commentRequestDto);
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "댓글 수정 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }

    public ResponseEntity<?> delete(Long commentId, User user) {
        Comment comment = getComment(commentId);
        if (!(comment.getUser().getId().equals(user.getId()) || user.getRole().equals(UserRoleEnum.ADMIN))) {
            throw new IllegalArgumentException("댓글 삭제 완료");
        }
        commentRepository.deleteById(commentId);
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "댓글 삭제 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
    }

    private Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
    }
}