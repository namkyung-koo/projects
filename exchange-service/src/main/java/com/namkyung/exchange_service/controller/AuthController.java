package com.namkyung.exchange_service.controller;

import com.namkyung.exchange_service.domain.User;
import com.namkyung.exchange_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 로그인 페이지 이동 (GET /login)
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // 로그인 처리 (POST /login)
//    @PostMapping("/login")
//    public String login(@RequestParam String userId, @RequestParam String password, Model model) {
//        User user = userService.findByUserIdAndPassword(userId, password);
//        if (user == null) {
//            return "redirect:/login?error";
//        }
//        model.addAttribute("username", user.getUsername());
//        return "main";
//    }

    // 회원 가입 페이지 이동 (GET /signup)
    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    // 회원가입 처리 (POST /signup)
    @PostMapping("/signup")
    public String registerUser(@RequestParam String username,
                               @RequestParam String userId,
                               @RequestParam String password,
                               @RequestParam String confirmPassword) {
        if (password.equals(confirmPassword)) {
            userService.createUser(username, userId, password);
            return "redirect:/login";
        }
        return "redirect:/signup?error";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout() {
        return "/redirect:/index";
    }
}
