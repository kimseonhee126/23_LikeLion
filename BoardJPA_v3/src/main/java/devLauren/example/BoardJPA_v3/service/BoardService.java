package devLauren.example.BoardJPA_v3.service;

import devLauren.example.BoardJPA_v3.domain.entity.Board;
import devLauren.example.BoardJPA_v3.domain.repository.BoardRepository;
import devLauren.example.BoardJPA_v3.dto.BoardDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository)
    {
        this.boardRepository = boardRepository;
    }

    // @Transactional
    /* 데이터베이스의 상태를 변경시키는 작업 */
    // 게시글 저장하는 코드
    @Transactional
    public Long savePost(BoardDto boardDto)
    {
        // .getId()
        /* 방금 저장한 데이터의 Id값을 얻을 수 있다 */
        return boardRepository.save(boardDto.toEntity()).getId();
    }


    // 게시글 목록 가져오기
    @Transactional
    public List<BoardDto> getBoardList()
    {
        // findAll()
        /* 조건없이 모든 튜플을 가져온다 */
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardList)
        {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            // boardDtoList 에 게시글 목록 객체를 순서대로 저장한다
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    // 게시글 가져오기
    @Transactional
    public BoardDto getPost(Long id)
    {
        // 해당 id의 게시글의 내용을 보여준다
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
        return boardDto;
    }

    // 게시글 삭제하기
    @Transactional
    public void deletePost(Long id)
    {
        boardRepository.deleteById(id);
    }
}
