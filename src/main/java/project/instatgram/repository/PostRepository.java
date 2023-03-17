package project.instatgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.instatgram.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
