package com.spring.study.controller;

import com.spring.study.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/login")
    public String loginView() {
       return "login";
    }

    /*
    * 로그인하는 메서드
    * 메서드명 : login
    * 반환타입 : String
    * */
    @PostMapping("/login")
    public String login(User user, boolean remember, String toURL, String active, Model model, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("active = " + active);
        // id저장 체크박스에 체크를 하고 로그인을 하면
        // 1. 재로그인 시 id 저장 checkbox에 check가 되어있어야 한다.
        // 2. id가 저장되어 있어야 한다.

        System.out.println("remember = " + remember);
        String userID = user.getId();

        // 1. 아이디와 비밀번호가 일치하는지 체크한다.
        if (!loginCheck(userID, user.getPwd())) {
            // 1-1. 일치하지 않으면 다시 login.jsp로 간다.
            return "redirect:/login";
        }

        // id를 session에 담는다.
        HttpSession session = request.getSession();
        session.setAttribute("id", userID);

        // 1. remember가 true인지 체크한다.
        if (remember) {
            // 1-1. true이면 쿠키를 생성해서 쿠키에 id를 저장한다.
            createCookie("id", userID, response);
        } else {
            // 1-2. false면 쿠키를 삭제한다.
            removeCookie("id", "", response);
        }


        // 만약 로그인이 안 되어있는 상태에서 Board 게시판에 접근하려고하면, 로그인 페이지로 redirect하고
        // 거기서 로그인에 성공했을 경우에 index.jsp가 아닌 board.jsp를 보여줘야 한다.
        String url = toURL == null || toURL.equals("") ? "/" : toURL;

        // 1-2. 일치하면 index.jsp로 가고
        return "redirect:" + url;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 로그아웃 시 세션 삭제
        HttpSession session = request.getSession();
        session.invalidate();

        return "index";
    }

    private void removeCookie(String name, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value); // 변경할 쿠키와 같은 이름의 쿠키 생성
        cookie.setMaxAge(0); // 유효기간을 0으로 설정(삭제)
        response.addCookie(cookie); // 응답에 쿠키 추가
    }

    private void createCookie(String name, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value); // 쿠키 생성
        response.addCookie(cookie); // 응답에 쿠키 추가
    }

    private boolean loginCheck(String id, String pwd) {
        return "asdf".equals(id) && "1234".equals(pwd);
    }

}
