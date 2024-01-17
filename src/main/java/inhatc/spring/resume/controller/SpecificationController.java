package inhatc.spring.resume.controller;

import inhatc.spring.resume.dto.PersonalFormDto;
import inhatc.spring.resume.dto.SpecificationFormDto;
import inhatc.spring.resume.service.SpecificationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@Transactional
@RequiredArgsConstructor
public class SpecificationController {

    private final SpecificationService specificationService;


    @GetMapping("/resume/spec")
    public String specificationForm(Model model) {

        try {
            SpecificationFormDto specificationFormDto = specificationService.getSpecificationDetail();
            model.addAttribute("specificationFormDto", specificationFormDto);
            return "resume/specificationForm";
        } catch (EntityNotFoundException e) {
            return "resume/specificationForm";
        }
    }

    @PostMapping("/resume/spec")
    public String specificationNew(@ModelAttribute("specificationFormDto") SpecificationFormDto specificationFormDto){

        specificationService.insertSpecification(specificationFormDto);

        return "redirect:/resume/view";
    }



}
