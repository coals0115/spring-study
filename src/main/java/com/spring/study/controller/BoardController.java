package com.spring.study.controller;

import com.spring.study.domain.BoardDto;
import com.spring.study.domain.PageHandler;
import com.spring.study.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    // board list를 받아와서 화면에 보여준다.
    public String board(Integer page, Integer pageSize, Model m) {
        try {
            int page_ = Objects.requireNonNullElse(page, 1);
            int pageSize_ = Objects.requireNonNullElse(pageSize, 10);
            int cnt = boardService.getCount();
            PageHandler pageHandler = new PageHandler(cnt, pageSize_, page_);

            Map<String, Integer> map = new HashMap<>();
            map.put("offset", (pageHandler.getCurPage() - 1) * pageHandler.getPageSize());
            map.put("pageSize", pageHandler.getPageSize());

            List<BoardDto> boardList = boardService.getPage(map);

            m.addAttribute("boardList", boardList);
            m.addAttribute("p", pageHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "boardList";
    }

    @GetMapping("/cardGame")
    public String cardGame() {
        return "cardGame";
    }
}
