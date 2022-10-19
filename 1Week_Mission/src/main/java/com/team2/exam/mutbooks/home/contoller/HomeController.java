package com.team2.exam.mutbooks.home.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("")
    public String showMain() {

      return "home/main";
    }
}
