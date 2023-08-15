package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.service.TakeCourseService;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TakeCourseService takeCourseService;

    @RequestMapping("/member/login") // get, post 둘 다 받을 수 있도록 requestmapping으로 설정
    public String login() {

        return "member/login";
    }

    @GetMapping("/member/find/password") // 비밀번호 찾기 위한 페이지
    public String findPassword() {

        return "member/find_password";
    }

    @PostMapping("/member/find/password")
    // 비밀번호 초기화를 위한 정보 parameter로 입력받으면 포스트 형태로 해당 주소에 매핑됨.
    public String findPasswordSubmit(Model model, ResetPasswordInput parameter) {


        boolean result = false;
        try {
            result = memberService.sendResetPassword(parameter);
        } catch (Exception e) {

        }
        model.addAttribute("result", result);

        return "member/find_password_result"; // 비밀번호 바꾸기 위한 메일 잘 보냈다 보여주는 페이지로 이동
    }


    @GetMapping("/member/register")
    public String register() {

        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model, // Model 은 Client 한테 데이터를 내리기 위해 사용되는 인터페이스
                                 HttpServletRequest request,
                                 MemberInput parameter) {

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result); // 현재 result에 대해 위에서 입력받은 result의 값을 넣어줌.

        return "member/register_complete";
    }

    @GetMapping("member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id"); // 타고 들어와지는 주소의 uuid 값을 받는 parameter 명이 id임.
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);

        model.addAttribute("result", result);

        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberInfo(Model model, Principal principal) {

        String userId = principal.getName();
        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail", detail);

        return "member/info";
    }

    @PostMapping("/member/info")
    public String memberInfoSubmit(Model model, MemberInput parameter, Principal principal) {

        String userId = principal.getName();
        parameter.setUserId(userId);

        ServiceResult result = memberService.updateMember(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }
        return "redirect:/member/info";
    }

    @GetMapping("/member/password")
    public String memberPassword(Model model, Principal principal) {

        String userId = principal.getName();
        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail", detail);

        return "member/password";
    }

    @PostMapping("/member/password")
    public String memberPasswordSubmit(Model model, MemberInput parameter, Principal principal) {

        String userId = principal.getName();
        parameter.setUserId(userId);

        ServiceResult result = memberService.updateMemberPassword(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/takecourse")
    public String memberTakeCourse(Model model, Principal principal) {

        String userId = principal.getName();
        List<TakeCourseDto> list = takeCourseService.myCourse(userId);

        model.addAttribute("list", list);

        return "member/takecourse";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");

        boolean result = memberService.checkResetPassword(uuid);

        model.addAttribute("result", result);

        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput parameter) {

        boolean result = false;

        try {
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword()); // 파라미터로 들어온 uuid가 Id로 가져와짐.
        } catch (Exception e) {

        }

        model.addAttribute("result", result);

        return "member/reset_password_result";
    }

}
