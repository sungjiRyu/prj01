package com.project1st.starbucks.admin.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping("/")
    public String getMain(Model model, HttpSession session) {
        System.out.println("getMain()");
        model.addAttribute("info", "model Info");
        session.setAttribute("info2","session Info");

        return "/index";
    }
}
