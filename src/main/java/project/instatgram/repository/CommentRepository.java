package project.instatgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.instatgram.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
