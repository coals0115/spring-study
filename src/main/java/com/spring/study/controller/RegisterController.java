package com.spring.study.controller;

import com.spring.study.dao.UserDao;
import com.spring.study.domain.User;
import com.spring.study.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller // 원격 호출이 가능한 프로그램으로 등록(AC의 빈으로 등록)
@RequestMapping("/register") //
public class RegisterController {
    @Autowired
    UserDao userDao;

    @InitBinder
    public void webDataBinderTest(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
        System.out.println("binder.getValidators() = " + binder.getValidators());
    }


    @GetMapping("/add") // '/add'로의 요청을 RegisterController의 registerForm() 메서드와 연결
    public String registerForm() {
        return "registerForm";
    }

    @PostMapping("/add")
    public String registerAdd(@Valid User user, BindingResult result, Model m) throws SQLException { // UserDao를 이용해서 DB에 사용자 정보를 저장한다.
        System.out.println("user = " + user);
        System.out.println("bindingResult = " + result);

        if (result.hasErrors()) {
            return "registerForm";
        }

        userDao.insertUser(user);

        return "redirect:/login/login";
    }

//    @PostMapping("/add") // 이렇게 간단하게 가능. 위와 동일한 의미
//    public String save(@Valid User user, BindingResult result, Model m) throws Exception {
//        System.out.println("result = " + result);
//        // User 객체를 검증한 결과가 에러가 있으면, registerForm을 이용해서 에러를 보여줘야 한다.
//        if (result.hasErrors()) {
//            return "registerForm";
//        }
//
//        // 2. DB에 신규 회원 정보 저장
//        return "registerInfo";
//    }
}
