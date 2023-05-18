package com.spring.study.controller;

import com.spring.study.dao.UserDao;
import com.spring.study.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping("/login")
public class LoginController {
   @Autowired
    UserDao userDao;

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
    public String login(User user, boolean remember, String toURL, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = user.getId();
            User loginCheckUser = userDao.selectUser(userId);

            // 1. 아이디와 비밀번호가 일치하는지 체크한다.
            if (!loginCheck(user, loginCheckUser)) {
                // 1-1. 일치하지 않으면 다시 login.jsp로 간다.
                return "redirect:/login";
            }

            // id를 session에 담는다.
            HttpSession session = request.getSession();
            session.setAttribute("id", userId);

            // 1. remember가 true인지 체크한다.
            if (remember) {
                // 1-1. true이면 쿠키를 생성해서 쿠키에 id를 저장한다.
                createCookie("id", userId, response);
            } else {
                // 1-2. false면 쿠키를 삭제한다.
                removeCookie("id", "", response);
            }
            // 1-2. 일치하면 index.jsp로 가고
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 만약 로그인이 안 되어있는 상태에서 Board 게시판에 접근하려고하면, 로그인 페이지로 redirect하고
        // 거기서 로그인에 성공했을 경우에 index.jsp가 아닌 board.jsp를 보여줘야 한다.
        String url = toURL == null || toURL.equals("") ? "/" : toURL;
//        String url = Objects.requireNonNullElse(toURL, "");
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

    private boolean loginCheck(User user, User loginCheckUser) {
        return user.getId().equals(loginCheckUser.getId()) && user.getPwd().equals(loginCheckUser.getPwd());
    }

}
