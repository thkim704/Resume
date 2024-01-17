package inhatc.spring.resume.controller;

import inhatc.spring.resume.dto.MemberFormDto;
import inhatc.spring.resume.dto.PersonalFormDto;
import inhatc.spring.resume.dto.SpecificationFormDto;
import inhatc.spring.resume.service.MemberService;
import inhatc.spring.resume.service.PersonalService;
import inhatc.spring.resume.service.SpecificationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final PersonalService personalService;
    private final SpecificationService specificationService;
    private final MemberService memberService;

    @GetMapping("/resume/view")
    public String specificationForm(Model model) {

        MemberFormDto memberFormDto = MemberFormDto.entityToDto(memberService.getMember());

        try {
            PersonalFormDto personalFormDto = personalService.getPersonalDetail();
            SpecificationFormDto specificationFormDto = specificationService.getSpecificationDetail();
            model.addAttribute("memberFormDto", memberFormDto);
            model.addAttribute("personalFormDto", personalFormDto);
            model.addAttribute("specificationFormDto", specificationFormDto);

            return "resume/resumeForm";
        } catch (EntityNotFoundException e){
            model.addAttribute("personalFormDto", new PersonalFormDto());
            model.addAttribute("errorMessage", "이력서를 작성하세요");
            return "resume/personalForm";
        }



    }
}
