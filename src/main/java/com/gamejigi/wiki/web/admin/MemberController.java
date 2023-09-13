package com.gamejigi.wiki.web.admin;

import com.gamejigi.wiki.domain.member.role.Role;
import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.service.category.CategoryService;
import com.gamejigi.wiki.util.PaginationRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import com.gamejigi.wiki.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.SourceVersion;
import java.beans.Encoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("admin/member")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
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
            @RequestParam Long id
    ) {
        var member = memberService.getById(id);
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
            @RequestParam String phone1,
            @RequestParam String phone2,
            @RequestParam String phone3,
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String birth1,
            @RequestParam String birth2,
            @RequestParam String birth3,
            @RequestParam Boolean gender,
            @RequestParam Role role
    ) {

        String birth4 = birth1 + "-" + birth2 + "-" + birth3;
        String phone = phone1 + "-" + phone2 + "-" + phone3;
        LocalDate birth = LocalDate.parse(birth4, DateTimeFormatter.ISO_DATE);

        memberService.createMember(user_id, pw, phone, email, name, birth, gender, role);

        return "";
    }

    @DeleteMapping("")
    @ResponseBody
    public String memberCreate(
            Model model,
            @RequestParam long id
    ) {
        var member = memberService.getById(id);
        model.addAttribute("member", member);
        memberService.delete(id);

        return "";
    }

    @GetMapping("/update")
    public String memberUpdate(
            Model model,
            @RequestParam Long id
    ) {
        var member = memberService.getById(id);
        model.addAttribute("member", member);
        return "admin/member/update";
    }

    @PatchMapping("")
    @ResponseBody
    public String memberPatch(
            Model model,
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam String user_id,
            @RequestParam String pw,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam LocalDate birth,
            @RequestParam Boolean gender,
            @RequestParam Role role,
            @RequestParam Boolean locked,
            @RequestParam(required = false) LocalDate lockedDate
    ) {
        var member = memberService.getById(id);
        model.addAttribute("member", member);
        String password;
        Boolean resultDate;
        LocalDate currentDate = LocalDate.now();

        if(lockedDate != null){
            resultDate = lockedDate.isAfter(currentDate);
        }
        else resultDate = false;
        
        if(pw == null) { //비밀번호를 고치지 않았다면 기존 비밀번호를 다시 저장
           password = member.getPw();
        }
        else{
            password = pw;
        }
        
        if(!locked && lockedDate != null){ //활동을 선택했는데 날짜를 입력한 경우 무효화
            lockedDate = null;
        }

        if(!resultDate){ //정지 날짜가 현재 날짜보다 이전인 경우 무효
            locked = false;
            lockedDate = null;
        }

        memberService.patch(id, user_id, password, phone, email, name, birth, gender, role, locked, lockedDate);

        return "";
    }

}
