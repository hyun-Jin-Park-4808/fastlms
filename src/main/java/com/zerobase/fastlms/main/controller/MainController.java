package com.zerobase.fastlms.main.controller;

import com.zerobase.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 템플릿 명이 return 됨.
@RequiredArgsConstructor
public class MainController {

    private final MailComponents mailComponents;

    @RequestMapping("/")
    public String index() {
//
//        String email = "guswlsdl4808@gmail.com";
//        String subject = "안녕하세요. 제로베이스 입니다.";
//        String text = "<p>안녕하세요.</p><p>반갑습니다.</p>";
//
//        mailComponents.sendMail(email, subject, text);

        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {

        return "error/denied";
    }
}
