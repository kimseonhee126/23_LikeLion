package com.devLauren.HtmlConnect.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }
}
