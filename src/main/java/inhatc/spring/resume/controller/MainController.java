package inhatc.spring.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(){

        return "main";
    }

    @GetMapping("/lab")
    public String hello(){

        return "resume/lab";
    }
}
