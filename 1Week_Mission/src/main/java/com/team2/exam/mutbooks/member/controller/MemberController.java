package com.team2.exam.mutbooks.member.controller;

import com.team2.exam.mutbooks.base.exception.AlreadyExistingMemberException;
import com.team2.exam.mutbooks.base.exception.AlreadyExistingNicknameException;
import com.team2.exam.mutbooks.member.dto.MemberJoinForm;
import com.team2.exam.mutbooks.member.dto.MemberLoginForm;
import com.team2.exam.mutbooks.member.entity.Member;
import com.team2.exam.mutbooks.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String showJoinForm(MemberJoinForm memberJoinForm) {
        return "member/join_form";
    }

    @PostMapping("/join")
    public String join(HttpServletRequest request, MemberJoinForm memberJoinForm) {
        log.info("memberJoinForm = {}", memberJoinForm);

        String enteredPassword = memberJoinForm.getPassword();
        String encodedPassword = passwordEncoder.encode(enteredPassword);

        try {
            memberService.join(memberJoinForm.getUsername(), encodedPassword, memberJoinForm.getEmail(), memberJoinForm.getNickname());
        } catch (AlreadyExistingMemberException e) {
            return "redirect:/member/join?error= Already Existing Member";
        } catch (AlreadyExistingNicknameException e) {
            return "redirect:/member/join?error= Already Existing Nickname";
        }

        try {
            request.login(memberJoinForm.getUsername(), enteredPassword);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm(MemberLoginForm memberLoginForm) {
        return "member/login_form";
    }
}
