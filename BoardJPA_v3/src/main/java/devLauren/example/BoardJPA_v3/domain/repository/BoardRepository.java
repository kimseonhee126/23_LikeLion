package devLauren.example.BoardJPA_v3.domain.repository;

import devLauren.example.BoardJPA_v3.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity 이름, 테이블의 Pk 데이터 타입>
public interface BoardRepository extends JpaRepository<Board, Long> {
}
