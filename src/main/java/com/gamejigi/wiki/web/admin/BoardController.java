package com.gamejigi.wiki.web.admin;


import com.gamejigi.wiki.config.auth.dto.CustomUserDetails;
import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.service.category.CategoryService;
import com.gamejigi.wiki.service.member.MemberService;
import com.gamejigi.wiki.util.PaginationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin/board")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class BoardController {
    public final BoardService boardService;
    public final CategoryService categoryService;
    public final MemberService memberService;

    @GetMapping("")
    public String boardList(
            Model model,
            HttpServletRequest httpServletRequest,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "", name = "type") String searchType,
            @RequestParam(required = false, defaultValue = "0") int sort,
            @RequestParam(required = false, defaultValue = "", name = "col") String sortColumn,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        var request = PaginationRequest.builder()
                .page(page)
                .maxSize(size)
                .search(search)
                .searchType(searchType)
                .sort(sort)
                .sortColumn(sortColumn)
                .build();
        var pagination = boardService.getBoardList(request);

        model.addAttribute("pagination", pagination);

        String parameter = httpServletRequest.getQueryString();
        parameter = parameter == null ? "" : parameter;

        model.addAttribute("parameter", parameter);

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
            @RequestParam(name = "category") long categoryId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        long suId = userDetails.getMember().getId();

        boardService.createBoard(name, categoryId, suId);

        return "";
    }

    @DeleteMapping("")
    @ResponseBody
    public String boardCreate(
            @RequestParam long id
    ) {
        boardService.delete(id);

        return "";
    }

    @GetMapping("/update")
    public String boardUpdate(
            Model model,
            @RequestParam int id
    ) {
        var board = boardService.getBoardById(id);
        model.addAttribute("board", board);

        var categories = categoryService.getCategoryList();
        model.addAttribute("categories", categories);

        return "admin/board/update";
    }

    @PatchMapping("")
    @ResponseBody
    public String boardPatch(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam(name = "category") long categoryId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        long suId = userDetails.getMember().getId();

        boardService.patch(id, suId, name, categoryId);

        return "";
    }

}
