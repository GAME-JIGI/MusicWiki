package com.gamejigi.wiki.web.admin;


import com.gamejigi.wiki.service.category.CategoryService;
import com.gamejigi.wiki.service.member.MemberService;
import com.gamejigi.wiki.util.PaginationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("admin/category")
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;
    public final MemberService memberService;

    @GetMapping("")
    public String categoryList(
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
        var pagination = categoryService.getCategoryList(request);

        model.addAttribute("pagination", pagination);

        String parameter = httpServletRequest.getQueryString();
        parameter = parameter == null ? "" : parameter;

        model.addAttribute("parameter", parameter);

        return "admin/category/list";
    }

    @PostMapping("/test")
    @ResponseBody
    public String categoryTestCreate() {

        categoryService.createTestcase();

        return "";
    }

    @DeleteMapping("/test")
    @ResponseBody
    public String categoryTestDelete() {

        categoryService.deleteTestcase();

        return "";
    }

    @GetMapping("/detail")
    public String categoryDetail(
            Model model,
            @RequestParam long id
    ) {
        var category = categoryService.getById(id);
        model.addAttribute("category", category);

        return "admin/category/detail";
    }

    @GetMapping("/create")
    public String categoryCreate() {
        return "admin/category/create";
    }

    @PostMapping("")
    @ResponseBody
    public String categoryCreate(
            @RequestParam String name
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        categoryService.createCategory(name, suId);

        return "";
    }

    @DeleteMapping("")
    @ResponseBody
    public String categoryCreate(
            @RequestParam long id
    ) {
        categoryService.delete(id);

        return "";
    }

    @GetMapping("/update")
    public String categoryUpdate(
            Model model,
            @RequestParam int id
    ) {
        var board = categoryService.getById(id);
        model.addAttribute("category", board);

        return "admin/category/update";
    }

    @PatchMapping("")
    @ResponseBody
    public String categoryPatch(
            @RequestParam long id,
            @RequestParam String name
    ) {
        //추후에 계정ID를 세션에서 가져오는 코드 필요
        long suId = memberService.getByName("admin").getId();

        categoryService.patch(id, suId, name);

        return "";
    }

}
