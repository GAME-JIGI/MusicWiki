package com.gamejigi.wiki.web.admin;


import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.service.document.DocumentService;
import com.gamejigi.wiki.service.category.CategoryService;
import com.gamejigi.wiki.service.member.MemberService;
import com.gamejigi.wiki.util.PaginationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/document")
@RequiredArgsConstructor
public class DocumentController {

    public final BoardService boardService;
    public final CategoryService categoryService;
    public final MemberService memberService;
    public final DocumentService documentService;

    @GetMapping("")
    public String documentList(
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


        var pagination = documentService.getDocumentList(request);//============

        model.addAttribute("pagination", pagination);

        return "admin/document/list";
    }

    @PostMapping("/test")
    @ResponseBody
    public String boardTestCreate() {

        boardService.createTestcase(); ////

        return "";
    }

    @DeleteMapping("/test")
    @ResponseBody
    public String boardTestDelete() {

        boardService.deleteTestcase(); //

        return "";
    }

    @GetMapping("/detail")
    public String boardDetail(
            Model model,
            @RequestParam long id
    ) {
        var board = documentService.getDocumentById(id); //
        model.addAttribute("board", board);

        return "admin/document/detail";
    }

    @GetMapping("/create")
    public String boardCreate(
            Model model
    ) {
        var categories = categoryService.getCategoryList(); //
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

        boardService.createBoard(name, categoryId, suId); //

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
            @RequestParam(name = "category") long categoryId
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        boardService.patch(id, suId, name, categoryId);

        return "";
    }



}
