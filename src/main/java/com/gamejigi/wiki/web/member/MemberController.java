package com.gamejigi.wiki.web.member;

import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.service.category.CategoryService;
import com.gamejigi.wiki.util.PaginationRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import com.gamejigi.wiki.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/member")
@RequiredArgsConstructor
public class MemberController {

    public final BoardService boardService;
    public final CategoryService categoryService;
    public final MemberService memberService;

    @GetMapping("")
    public String memberList(
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

        return "admin/member/list";
    }

    @PostMapping("/test")
    @ResponseBody
    public String memberTestCreate() {

        memberService.createTestcase();

        return "";
    }

    @DeleteMapping("/test")
    @ResponseBody
    public String memberTestDelete() {

        memberService.deleteTestcase();

        return "";
    }

    @GetMapping("/detail")
    public String memberDetail(
            Model model,
            @RequestParam String name
    ) {
        var member = memberService.getByName(name);
        model.addAttribute("member", member);

        return "admin/member/detail";
    }

    @GetMapping("/create")
    @ResponseBody
    public String memberCreate(
            @RequestParam String name,
            @RequestParam(name = "member") long memberId
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        //memberService.createMember(memberId,);

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

        return "admin/member/update";
    }

    @PatchMapping("")
    @ResponseBody
    public String boardPatch(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam(name = "category") long categoryId
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        boardService.patch(id, suId, name, categoryId);

        return "";
    }

}
