package devLauren.example.BoardJPA_v3.controller;
import devLauren.example.BoardJPA_v3.dto.BoardDto;
import devLauren.example.BoardJPA_v3.service.BoardService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService)
    {
        this.boardService = boardService;
    }
    @GetMapping("/")
    public String list(Model model)
    {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        // View 에 데이터를 전달하기 위해(Java -> Json)
        // 모델에 데이터를 담기 위해 .addAttribute()를 사용한다
        /* .addAttribute(String name, Object value)
        * value 객체를 'name' 이름으로 저장하고
        * view 에서 name 을 사용해서 value를 사용한다*/
        model.addAttribute("postList", boardDtoList);
        return "board/list.html";
    }

    @GetMapping("/post")
    public String post()
    {
        return "board/post.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto)
    {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model)
    {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";
    }

    @PutMapping("/post/edit/{id}")
    public String update(BoardDto boardDto)
    {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        boardService.deletePost(id);
        return "redirect:/";
    }
}
