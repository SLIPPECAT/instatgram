package project.instatgram.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {

    // 처음 만들었을때의 시간
    @CreatedDate
    private LocalDateTime createdAt;

    // 마지막 수정한 시간
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}

