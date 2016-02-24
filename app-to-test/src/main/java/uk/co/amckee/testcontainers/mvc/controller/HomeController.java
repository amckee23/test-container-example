package uk.co.amckee.testcontainers.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(@RequestParam(value = "user", required = false, defaultValue = "") String user, Model model) {
        model.addAttribute("user", user);
        return "home";
    }


}
