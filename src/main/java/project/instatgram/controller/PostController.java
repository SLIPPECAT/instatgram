package project.instatgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.instatgram.dto.PostRequestDto;
import project.instatgram.dto.PostResponseDto;
import project.instatgram.security.UserDetailsImpl;
import project.instatgram.service.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 게시글 등록(입력)
    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(postRequestDto, userDetails.user());
    }
    // 게시글 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getPostList() {
        return postService.findAllPost();
    }
    // 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.delete(postId, userDetails.user());
    }
    // 수정
    @PatchMapping("/posts/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        String userId = userDetails.getUsername();
        return postService.updatePost(postId, postRequestDto, userId);
    }
}
