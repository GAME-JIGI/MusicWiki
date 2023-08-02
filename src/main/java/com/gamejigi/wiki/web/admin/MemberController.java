package com.gamejigi.wiki.web.admin;

import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.service.category.CategoryService;
import com.gamejigi.wiki.util.PaginationRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import com.gamejigi.wiki.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.beans.Encoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("admin/member")
@RequiredArgsConstructor
public class MemberController {

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
        var pagination = memberService.getMemberList(request);

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
            @RequestParam String id
    ) {
        var member = memberService.getByName(id);
        model.addAttribute("member", member);

        return "admin/member/detail";
    }

    @GetMapping("/create")
    public String memberCreate() {
        return "admin/member/create";
    }

    @PostMapping("")
    @ResponseBody
    public String memberCreate(
            @RequestParam String user_id,
            @RequestParam String pw,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam LocalDate birth,
            @RequestParam Boolean is_su,
            @RequestParam Role role
    ) {

//        String birth4 = birth1 + birth2 + birth3;
//        LocalDate birth = LocalDate.parse(birth4, DateFormatter.ISO_DATE);

        memberService.createMember(user_id, pw, phone, email, name, birth, is_su, role);

        return "";
    }

    @DeleteMapping("")
    @ResponseBody
    public String memberCreate(
            @RequestParam long id
    ) {
        memberService.delete(id);

        return "";
    }

    @GetMapping("/update")
    public String memberUpdate() {
        return "admin/member/update";
    }

    @PatchMapping("")
    @ResponseBody
    public String memberPatch(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam String user_id,
            @RequestParam String pw,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam LocalDate birth,
            @RequestParam Boolean is_su,
            @RequestParam Role role
    ) {

        memberService.patch(id, user_id, pw, phone, email, name, birth, is_su, role);

        return "";
    }

}
