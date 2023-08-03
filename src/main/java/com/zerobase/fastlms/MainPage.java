package com.zerobase.fastlms;

// MainPage 클래스를 만든 목적
// 논리적인 주소(인터넷 주소)와 물리적인 파일(프로그래밍한 파일) 매핑
// 하나의 주소에 대해 어디서 매핑하고 누가 매핑?
// 매핑 대상 후보군: 클래스, 속성, 메소드 => 메소드가 가장 적절

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPage {
    @RequestMapping("/")
    public String index() {
        return "Index Page";
    }

    @RequestMapping("/hello")
    public String hello() {
//        String msg = "hello \r\n fastlms website!!"; // 이렇게만 하면 줄바꿈 반영 안 됨.
        String msg = "<p>hello</p> <p>fastlms website!!</p>"; // 이렇게 태그 써줘야 줄바꿈 반영 됨.

        return msg;
    }

}
