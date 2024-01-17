package inhatc.spring.resume.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass                                       // 해당 클래스를 상속받는 클래스에게 속성만 제공
@Getter
@Setter
public abstract class BaseTimeEntity {


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regTime;      // 등록 시간

    @LastModifiedDate
    private LocalDateTime updateTime;   // 수정 시간
}
