package inhatc.spring.resume.controller;

import inhatc.spring.resume.dto.PersonalFormDto;
import inhatc.spring.resume.service.PersonalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class PersonalController {

    private final PersonalService personalService;

    @Value("${personalImgLocation}")
    private String personalImgLocation;

    @GetMapping("/resume/personal")
    public String personalForm(Model model) {

        try {
            PersonalFormDto personalFormDto = personalService.getPersonalDetail();
            model.addAttribute("personalFormDto", personalFormDto);
            return "resume/personalForm";
        } catch (EntityNotFoundException e){
            model.addAttribute("personalFormDto", new PersonalFormDto());
            return "resume/personalForm";
        }
    }

    @PostMapping("/resume/personal")
    public String personalNew(@Valid PersonalFormDto personalFormDto, @RequestParam("personalImgFile") MultipartFile personalImgFile,
                              BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "resume/personalForm";
        }

        try {
            personalService.savePersonal(personalFormDto, personalImgFile);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "신상 정보 등록 중 오류가 발생했습니다.");
            return "resume/personalForm";
        }

        return "redirect:/resume/spec";
    }

    @PostMapping("/resume/{memberId}")
    public String personalUpdate(@Valid PersonalFormDto personalFormDto, @RequestParam("personalImgFile") MultipartFile personalImgFile,
                                 BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "resume/personalForm";
        }

        try {
            personalService.updatePersonal(personalFormDto, personalImgFile);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "신상 정보 수정 중 오류가 발생했습니다.");
            return "resume/personalForm";
        }

        return "redirect:/resume/spec";
    }
}
