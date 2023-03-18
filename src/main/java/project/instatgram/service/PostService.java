package project.instatgram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import project.instatgram.dto.PostRequestDto;
import project.instatgram.dto.PostResponseDto;
import project.instatgram.entity.Post;
import project.instatgram.entity.User;
import project.instatgram.repository.PostRepository;
import project.instatgram.repository.UserRepository;
import project.instatgram.responsedto.StatusResponseDto;
import project.instatgram.security.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    // 등록
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;

    }
    // 게시글 전체 조회
    public List<PostResponseDto> findAllPost() {
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        List<Post> postList = postRepository.findAll();
        for (Post post : postList) {
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return postResponseDtoList;
    }

    // 삭제
    public ResponseEntity<Object> delete(Long id, String userId) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        // 이거 쓴 사람이랑 로그인 된 사람일치
        if(!userId.equals(post.getUser().getUsername())){
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }
        postRepository.delete(post);
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "게시물 삭제 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }
    // 수정
    @Transactional
    public ResponseEntity<Object> updatePost(Long id, PostRequestDto postRequestDto, String userId) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        // 이거 쓴 사람이랑 로그인 된 사람일치
        if(!userId.equals(post.getUser().getUsername())){
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }

        post.updatePost(postRequestDto);
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "게시물 수정 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }
    //


}

