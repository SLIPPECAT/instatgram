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
    public ResponseEntity delete(Long id) {
        Post post = getpost(id);
        postRepository.deleteById(id);
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "게시물 삭제 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }
    // 수정
    @Transactional
    public ResponseEntity updatePost(Long id, PostRequestDto postRequestDto) {
        Post post = getpost(id);
        post.updatePost(postRequestDto);
        StatusResponseDto statusResponseDto = new StatusResponseDto(HttpStatus.OK.value(), "게시물 수정 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(statusResponseDto);
    }
    //
    private Post getpost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("추후에 커스텀!")
        );
    }

    // 게시물을 작성한사람
    //지금 로그인한사람이 동일한지
    //
    //그리고 그사람이 일반 유저인지 어드민인지
}

