package com.gamejigi.wiki.web.admin;


import com.gamejigi.wiki.domain.boardAnnounce.BoardAnnounce;
import com.gamejigi.wiki.service.BoardAnnounce.BoardAnnounceService;
import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.service.category.CategoryService;
import com.gamejigi.wiki.service.member.MemberService;
import com.gamejigi.wiki.util.PaginationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/boardannounce")
@RequiredArgsConstructor
public class BoardAnnounceController {

    public final BoardService boardService;
    public final BoardAnnounceService boardAnnounceService;
    public final MemberService memberService;

    @GetMapping("")
    public String boardAnnounceList(
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
        var pagination = boardAnnounceService.getBoardAnnounceList(request);

        model.addAttribute("pagination", pagination);

        String parameter = httpServletRequest.getQueryString();
        parameter = parameter == null ? "" : parameter;

        model.addAttribute("parameter", parameter);

        return "admin/boardAnnounce/list";
    }

    @PostMapping("/test")
    @ResponseBody
    public String boardAnnounceTestCreate() {

        boardAnnounceService.createTestcase();

        return "";
    }

    @DeleteMapping("/test")
    @ResponseBody
    public String boardAnnounceTestDelete() {

        boardAnnounceService.deleteTestcase();

        return "";
    }

    @GetMapping("/detail")
    public String boardAnnounceDetail(
            Model model,
            @RequestParam long id
    ) {
        var boardAnnounce = boardAnnounceService.getBoardAnnounceById(id);
        model.addAttribute("boardannounce", boardAnnounce);

        return "admin/boardAnnounce/detail";
    }

    @GetMapping("/create")
    public String boardAnnounceCreate(
            Model model
    ) {
        var boards = boardService.getBoardList();
        model.addAttribute("boards", boards);

        return "admin/boardAnnounce/create";
    }

    @PostMapping("")
    @ResponseBody
    public String boardAnnounceCreate(
            @RequestParam String title,
            @RequestParam(name = "board") long boardId
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        boardAnnounceService.createBoardAnnounce(title, boardId, suId);

        return "";
    }

    @DeleteMapping("")
    @ResponseBody
    public String boardAnnounceCreate(
            @RequestParam long id
    ) {
        boardAnnounceService.delete(id);

        return "";
    }

    @GetMapping("/update")
    public String boardAnnounceUpdate(
            Model model,
            @RequestParam int id
    ) {
        var boardAnnounce = boardAnnounceService.getBoardAnnounceById(id);
        model.addAttribute("boardannounce", boardAnnounce);

        var boards = boardService.getBoardList();
        model.addAttribute("board", boards);

        return "admin/boardAnnounce/update";
    }

    @PatchMapping("")
    @ResponseBody
    public String boardAnnouncePatch(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam(name = "board") long boardId
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        boardAnnounceService.patch(id, suId, name, boardId);

        return "";
    }

}
