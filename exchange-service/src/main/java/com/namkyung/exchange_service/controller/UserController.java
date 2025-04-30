package com.namkyung.exchange_service.controller;

import com.namkyung.exchange_service.domain.entity.User;
import com.namkyung.exchange_service.exception.AbsentUserIdException;
import com.namkyung.exchange_service.exception.DuplicateUserIdException;
import com.namkyung.exchange_service.exception.WrongPasswordException;
import com.namkyung.exchange_service.service.AuthService;
import com.namkyung.exchange_service.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

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
    @PostMapping("/login")
    public String login(@RequestParam String userId,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        try {
            User user = authService.login(userId, password);
            session.setAttribute("loginUser", user);
            model.addAttribute("username", user.getUsername());
            model.addAttribute("wallet", user.getWallet());
            return "main";
        } catch (AbsentUserIdException | WrongPasswordException e) {
            log.warn("로그인 실패: {}", e.getMessage());
            return "redirect:/login?error";
        }
    }

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
            try {
                userService.register(username, userId, password);
                return "redirect:/login";
            } catch (DuplicateUserIdException e) {
                log.warn("로그인 실패: {}", e.getMessage());
                return "redirect:/signup?error";
            }
        }
        return "redirect:/signup?error";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 로그인 정보 제거
        return "redirect:/";
    }

    @GetMapping("/main")
    public String showMainPage(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", loginUser.getUsername());
        model.addAttribute("wallet", loginUser.getWallet());
        return "main";
    }
}
