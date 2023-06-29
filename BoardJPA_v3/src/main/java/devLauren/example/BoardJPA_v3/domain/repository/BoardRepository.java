package devLauren.example.BoardJPA_v3.domain.repository;

import devLauren.example.BoardJPA_v3.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
