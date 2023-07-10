package devLauren.example.BoardJPA_v3.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
// @Entity
// 데이터베이스 테이블과 mapping되는 객체
@Entity
// @NoArgsConstructor
/* 무분별한 객체 생성을 하지 않기 위해 사용
   기본 생성자 접근 제어를 PROTECTED로 설정 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @EntityListeners
/*이벤트가 발생할 때 콜백을 처리하고 코드를 실행하는 방법
  Entity의 변화를 감지하고 테이블의 데이터를 변경하는 일을 한다 */
// AuditingEntityListener
/* Entity가 생성되고 변경되는 시점을 감지하여 기록하는 역할을 한다 */
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 10, nullable = false)
    private String author;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    // LocalDateTime
    /* 컴퓨터 현재 날짜와 시간을 기록하기 위해 */
    private LocalDateTime createdDate;

    // @LastModifiedDate
    /* 수정된 시간 정보를 사용하기 위해 */
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    // @Builder
    /* 객체를 생성하는데 있어 주입하는 방식 */
    // 객체를 따로 생성하지 않고 클래스로 만들어서 this 객체 사용
    // 아래와 같은 형식은 Lombok 형식
    @Builder
    public Board(Long id, String author, String title, String content)
    {
        // 각 인자에 대한 파라미터가 제대로 들어가는 것을 볼 수 있다
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
