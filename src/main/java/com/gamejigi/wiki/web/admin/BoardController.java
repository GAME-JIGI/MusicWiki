package com.gamejigi.wiki.web.admin;


import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.util.PaginationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/board")
@RequiredArgsConstructor
public class BoardController {

    public final BoardService boardService;

    @GetMapping("")
    public String boardList(
            Model model,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "", name = "type") String searchType,
            @RequestParam(required = false, defaultValue = "0") int sort,
            @RequestParam(required = false, defaultValue = "", name = "col") String sortColumn
    ) {

        var request = PaginationRequest.builder()
                .page(page)
                .search(search)
                .searchType(searchType)
                .sort(sort)
                .sortColumn(sortColumn)
                .build();
        var pagination = boardService.getBoardList(request);

        model.addAttribute("pagination", pagination);
        return "admin/board";
    }

    @PostMapping("/test")
    public String boardTestCreate() {

        boardService.createTestcase();

        return "redirect:/admin/board";
    }

    @DeleteMapping("/test")
    public String boardTestDelete() {

        boardService.deleteTestcase();

        return "redirect:/admin/board";
    }

}
