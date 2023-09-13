package com.gamejigi.wiki.web.admin;

import com.gamejigi.wiki.service.debate.DebateService;
import com.gamejigi.wiki.service.debateComment.DebateCommentService;
import com.gamejigi.wiki.service.document.DocumentService;
import com.gamejigi.wiki.service.member.MemberService;
import com.gamejigi.wiki.util.PaginationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/debateComment")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DebateCommentController {

    public final DebateCommentService debateCommentService;
    public final DebateService debateService;
    public final DocumentService documentService;
    public final MemberService memberService;

    @GetMapping("")
    public String debateCommentList(
            Model model,
            HttpServletRequest httpServletRequest,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "name", name = "type") String searchType,
            @RequestParam(required = false, defaultValue = "0") int sort,
            @RequestParam(required = false, defaultValue = "", name = "col") String sortColumn,
            @RequestParam(required = false, defaultValue = "10") int size
    ){

        var request = PaginationRequest.builder()
                .page(page)
                .maxSize(size)
                .search(search)
                .searchType(searchType)
                .sort(sort)
                .sortColumn(sortColumn)
                .build();
        var pagination = debateCommentService.getDebateCommentList(request);

        model.addAttribute("pagination", pagination);

        String parameter = httpServletRequest.getQueryString();
        parameter = parameter == null ? "" : parameter;

        model.addAttribute("parameter", parameter);

        return "admin/debateComment/list";
    }

    @PostMapping("/test")
    @ResponseBody
    public String debateCommentTestCreate() {

        debateCommentService.createTestcase();

        return "";
    }

    @DeleteMapping("/test")
    @ResponseBody
    public String debateCommentTestDelete() {

        debateCommentService.deleteTestcase();

        return "";
    }

    @GetMapping("/detail")
    public String debateCommentDetail(
            Model model,
            @RequestParam long id
    ) {
        var debateComment = debateCommentService.getDebateCommentById(id);
        model.addAttribute("debateComment", debateComment);

        return "admin/debateComment/detail";
    }

    @GetMapping("/create")
    public String debateCommentCreate(
            Model model
    ) {
        var documents = documentService.getDocumentList();
        model.addAttribute("documents", documents);

        var debates = debateService.getDebateList();
        model.addAttribute("debates", debates);

        return "admin/debateComment/create";
    }

    @PostMapping("")
    @ResponseBody
    public String debateCommentCreate(
            @RequestParam String name,
            @RequestParam(name = "document") long documentId,
            @RequestParam(name = "debate") long debateId
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        debateCommentService.createDebateComment(name, documentId, debateId ,suId);

        return "";
    }

    @DeleteMapping("")
    @ResponseBody
    public String debateCommentCreate(
            @RequestParam long id
    ) {
        debateCommentService.delete(id);

        return "";
    }

    @GetMapping("/update")
    public String debateCommentUpdate(
            Model model,
            @RequestParam int id
    ) {
        var debateComment = debateCommentService.getDebateCommentById(id);
        model.addAttribute("debateComment", debateComment);

        var documents = documentService.getDocumentList();
        model.addAttribute("documents", documents);

        var debates = debateService.getDebateList();
        model.addAttribute("debates", debates);


        return "admin/debateComment/update";
    }

    @PatchMapping("")
    @ResponseBody
    public String debateCommentPatch(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam(name = "document") long documentId,
            @RequestParam(name = "debate") long debateId
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        debateCommentService.patch(id, suId, name, documentId, debateId);

        return "";
    }


}
