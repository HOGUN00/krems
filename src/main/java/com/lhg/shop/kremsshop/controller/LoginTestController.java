package com.lhg.shop.kremsshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginTestController {
    @GetMapping("/login-success")
    public String success(@RequestParam String accessToken) {
        return "로그인 성공! 발급된 토큰: " + accessToken;
    }
}
