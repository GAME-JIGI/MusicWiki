package com.gamejigi.wiki.web.wiki;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wiki")
public class MainController {

    @GetMapping("")
    public String mainPage() {
        return "wiki/main";
    }

}
