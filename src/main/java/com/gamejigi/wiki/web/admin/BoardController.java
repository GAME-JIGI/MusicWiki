package com.gamejigi.wiki.web.admin;


import com.gamejigi.wiki.domain.board.Board;
import com.gamejigi.wiki.domain.category.Category;
import com.gamejigi.wiki.domain.member.Member;
import com.gamejigi.wiki.repository.member.MemberRepository;
import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.service.category.CategoryService;
import com.gamejigi.wiki.service.member.MemberService;
import com.gamejigi.wiki.util.PaginationRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
@RequestMapping("admin/board")
@RequiredArgsConstructor
public class BoardController {

    public final BoardService boardService;
    public final CategoryService categoryService;
    public final MemberService memberService;

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
        return "admin/board/list";
    }

    @PostMapping("/test")
    @ResponseBody
    public String boardTestCreate() {

        boardService.createTestcase();

        return "";
    }

    @DeleteMapping("/test")
    @ResponseBody
    public String boardTestDelete() {

        boardService.deleteTestcase();

        return "";
    }

    @GetMapping("/detail")
    public String boardDetail(
            Model model,
            @RequestParam long id
    ) {
        var board = boardService.getBoardById(id);
        model.addAttribute("board", board);

        return "admin/board/detail";
    }

    @GetMapping("/create")
    public String boardCreate(
            Model model
    ) {
        var categories = categoryService.getCategoryList();
        model.addAttribute("categories", categories);

        return "admin/board/create";
    }

    @PostMapping("")
    @ResponseBody
    public String boardCreate(
            @RequestParam String name,
            @RequestParam(name = "category") long categoryId
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        boardService.createBoard(name, categoryId, suId);

        return "";
    }

    @GetMapping("/update")
    public String boardUpdate(
            Model model,
            @RequestParam int id
    ) {
        return "admin/board/update";
    }

}
