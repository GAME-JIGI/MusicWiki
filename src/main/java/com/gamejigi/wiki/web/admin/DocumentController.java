package com.gamejigi.wiki.web.admin;



import com.gamejigi.wiki.service.document.DocumentService;

import jakarta.servlet.http.HttpServletRequest;
import com.gamejigi.wiki.service.member.MemberService;
import com.gamejigi.wiki.util.PaginationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/document")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DocumentController {
    public final MemberService memberService;
    public final DocumentService documentService;

    @GetMapping("")
    public String documentList(
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
                .search(search)
                .searchType(searchType)
                .sort(sort)
                .sortColumn(sortColumn)
                .build();

        var pagination = documentService.getDocumentList(request);

        model.addAttribute("pagination", pagination);

        String parameter = httpServletRequest.getQueryString();
        parameter = parameter == null ? "" : parameter;

        model.addAttribute("parameter", parameter);

        return "admin/document/list";
    }


    @GetMapping("/detail")
    public String documentDetail(
            Model model,
            @RequestParam long id
    ) {
        var document = documentService.getDocumentById(id); //
        model.addAttribute("document", document);

        return "admin/document/detail";
    }


    @GetMapping("/create")
    public String documentCreate(
            Model model
    ) {
        return "admin/document/create";
    }



    @PostMapping("")
    @ResponseBody
    public String documentCreate(
            @RequestParam String name,
            @RequestParam String text
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        documentService.createDocument(name, text, suId); //

        return "";
    }

    @DeleteMapping("")
    @ResponseBody
    public String documentDelete(
            @RequestParam long id
    ) {
        documentService.delete(id);

        return "";
    }

    @GetMapping("/update")
    public String documentUpdate(
            Model model,
            @RequestParam int id
    ) {
        var document = documentService.getDocumentById(id);
        model.addAttribute("document", document);

        return "admin/document/update";
    }

    @PatchMapping("")
    @ResponseBody
    public String documentPatch(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam String text
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        documentService.patch(id, suId, name, text);

        return "";
    }



}
