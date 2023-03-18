package project.instatgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.instatgram.dto.CommentRequestDto;
import project.instatgram.dto.CommentResponseDto;
import project.instatgram.security.UserDetailsImpl;
import project.instatgram.service.CommentService;

@RestController
@RequiredArgsConstructor

public class CommentController {

    private final CommentService commentService;

    @CrossOrigin
    @PostMapping("/api/comment/{postId}")
    public CommentResponseDto createComment
            (@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(postId, commentRequestDto, userDetails.user());
    }
    @CrossOrigin
    @PatchMapping("/api/comment/{commentId}")
    public ResponseEntity<?> updateComment
            (@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(commentId,commentRequestDto,userDetails.user());
    }
    @CrossOrigin
    @DeleteMapping("api/comment/{commentId}")
    public ResponseEntity<?> deleteComment
            (@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.delete(commentId, userDetails.user());
    }
}
