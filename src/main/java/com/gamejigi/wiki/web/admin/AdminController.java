package com.gamejigi.wiki.web.admin;

import com.gamejigi.wiki.service.board.BoardService;
import com.gamejigi.wiki.util.PaginationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping(value = {"", "/"})
    public String mainPage() {
        return "admin/main";
    }

}
