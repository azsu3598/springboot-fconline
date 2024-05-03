package me.projects.fconlinedemo.Controller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
//@RestController
@Controller
public class home {
    @GetMapping("/")
    public String Home(){
        return "main.html";
    }
}
