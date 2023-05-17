package com.spring.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/list")
    public String board(HttpServletRequest request) {
        System.out.println("/board/listadkfjaldfjladjflajdf;kjal;dk");
        // 로그인 체크는 로그인 필터가 대신함
        return "boardList";
    }

    @GetMapping("/cardGame")
    public String cardGame() {
        return "cardGame";
    }
}
