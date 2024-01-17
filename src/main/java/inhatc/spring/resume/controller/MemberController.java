package inhatc.spring.resume.controller;

import inhatc.spring.resume.dto.MemberFormDto;
import inhatc.spring.resume.entity.Member;
import inhatc.spring.resume.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/member/new")
    public String memberForm(Model model){

        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }



    @PostMapping("/member/new")
    public String insertMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

        if(!memberFormDto.getPassword().equals(memberFormDto.getPassword_confirm())){
            model.addAttribute("chkpwErrorMsg", "비밀번호가 일치하지 않습니다.");
            return "/member/memberForm";
        }

        if(bindingResult.hasErrors()){
            return "/member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/memberForm";
        }
        return "redirect:/member/login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "member/memberLoginForm";
    }

    @GetMapping("/member/logout")
    public String logoutForm(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    @GetMapping("/member/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호가 잘못되었습니다.");
        return "/member/memberLoginForm";
    }


    @GetMapping("/member/update")
    public String memberUpdateForm(Model model){
        MemberFormDto memberFormDto = memberService.getMemberDetail();

        model.addAttribute("memberFormDto", memberFormDto);
        return "member/memberUpdateForm";
    }

    @PostMapping("/member/update")
    public String updateMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "/member/memberUpdateForm";
        }

        try {
            System.out.println(memberFormDto.getName());
            memberService.updateMember(memberFormDto);

        } catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/memberUpdateForm";
        }
        return "redirect:/";
    }
}
