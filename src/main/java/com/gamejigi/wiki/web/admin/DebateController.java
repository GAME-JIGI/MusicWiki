package com.gamejigi.wiki.web.admin;

import com.gamejigi.wiki.service.debate.DebateService;
import com.gamejigi.wiki.service.document.DocumentService;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;


@Controller
@RequestMapping("admin/debate")
@RequiredArgsConstructor
public class DebateController {

    public final DebateService debateService;
    public final DocumentService documentService;
    public final MemberService memberService;

    @GetMapping("")
    public String debateList(
            Model model,
            HttpServletRequest httpServletRequest,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "", name = "type") String searchType,
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
        var pagination = debateService.getDebateList(request);

        model.addAttribute("pagination", pagination);

        String parameter = httpServletRequest.getQueryString();
        parameter = parameter == null ? "" : parameter;

        model.addAttribute("parameter", parameter);

        return "admin/debate/list";
    }

    @PostMapping("/test")
    @ResponseBody
    public String debateTestCreate() {

        debateService.createTestcase();

        return "";
    }

    @DeleteMapping("/test")
    @ResponseBody
    public String debateTestDelete() {

        debateService.deleteTestcase();

        return "";
    }

    @GetMapping("/detail")
    public String debateDetail(
            Model model,
            @RequestParam long id
    ) {
        var debate = debateService.getDebateById(id);
        model.addAttribute("debate", debate);

        return "admin/debate/detail";
    }

    @GetMapping("/create")
    public String debateCreate(
            Model model
    ) {
        var documents = documentService.getDocumentList();
        model.addAttribute("documents", documents);

        return "admin/debate/create";
    }

    @PostMapping("")
    @ResponseBody
    public String debateCreate(
            @RequestParam String name,
            @RequestParam(name = "document") long documentId,
            @RequestParam Integer commentAble
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        debateService.createDebate(name, documentId, suId, commentAble);

        return "";
    }

    @DeleteMapping("")
    @ResponseBody
    public String debateCreate(
            @RequestParam long id
    ) {
        debateService.delete(id);

        return "";
    }

    @GetMapping("/update")
    public String debateUpdate(
            Model model,
            @RequestParam int id
    ) {
        var debate = debateService.getDebateById(id);
        model.addAttribute("debate", debate);

        var documents = documentService.getDocumentList();
        model.addAttribute("documents", documents);

        return "admin/debate/update";
    }

    @PatchMapping("")
    @ResponseBody
    public String debatePatch(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam(name = "document") long documentId,
            @RequestParam Integer commentAble
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        debateService.patch(id, suId, name, documentId, commentAble);

        return "";
    }

}
