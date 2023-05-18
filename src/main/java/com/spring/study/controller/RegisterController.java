package com.spring.study.controller;

import com.spring.study.dao.UserDao;
import com.spring.study.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller // 원격 호출이 가능한 프로그램으로 등록(AC의 빈으로 등록)
@RequestMapping("/register") //
public class RegisterController {
    @Autowired
    UserDao userDao;

    @GetMapping("/add") // '/add'로의 요청을 RegisterController의 registerForm() 메서드와 연결
    public String registerForm() {
        return "registerForm";
    }

    @PostMapping("/add")
    public String registerAdd(User user) { // UserDao를 이용해서 DB에 사용자 정보를 저장한다.
        System.out.println("user = " + user);
        try {
            userDao.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "redirect:/login/login";
    }

}
