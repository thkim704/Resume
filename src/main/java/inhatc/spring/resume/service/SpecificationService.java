package inhatc.spring.resume.service;

import inhatc.spring.resume.dto.*;
import inhatc.spring.resume.entity.*;
import inhatc.spring.resume.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecificationService {

    private final EducationRepository educationRepository;
    private final CareerRepository careerRepository;
    private final CertificateRepository certificateRepository;
    private final AwardRepository awardRepository;
    private final LanguageRepository languageRepository;
    private final MemberService memberService;

    public void insertSpecification(SpecificationFormDto specificationFormDto){

        List<EducationDto> educationDtoList = specificationFormDto.getEducationDtoList();
        List<CareerDto> careerDtoList = specificationFormDto.getCareerDtoList();
        List<CertificateDto> certificateDtoList = specificationFormDto.getCertificateDtoList();
        List<AwardDto> awardDtoList = specificationFormDto.getAwardDtoList();
        List<LanguageDto> languageDtoList = specificationFormDto.getLanguageDtoList();

        List<Long> educationIds = specificationFormDto.getEducationIds();
        List<Long> careerIds = specificationFormDto.getCareerIds();
        List<Long> certificateIds = specificationFormDto.getCertificateIds();
        List<Long> awardIds = specificationFormDto.getAwardIds();
        List<Long> languageIds = specificationFormDto.getLanguageIds();

        //사용자 정보 얻어오기
        Member member = memberService.getMember();

        //학력 수정
        for (int i = 0; i < educationDtoList.size(); i++) {
            EducationDto educationDto = educationDtoList.get(i);
            if(educationIds.get(i) != -1){
                if(!educationDto.emptyEducation()){
                    Education education = educationRepository.findById(educationIds.get(i)).orElseThrow();
                    education.updateEducation(educationDto);
                }else {
                    educationRepository.deleteById(educationIds.get(i));
                }
            } else{
                if(!educationDto.emptyEducation()){
                    Education education = educationDto.createEducation();
                    education.setMember(member);
                    educationRepository.save(education);
                }
            }

        }

        //경력 수정
        for (int i = 0; i < careerDtoList.size(); i++) {
            CareerDto careerDto = careerDtoList.get(i);
            if(careerIds.get(i) != -1){
                if(!careerDto.emptyCareer()){
                    Career career = careerRepository.findById(careerIds.get(i)).orElseThrow();
                    career.updateCareer(careerDto);
                }else {
                    careerRepository.deleteById(careerIds.get(i));
                }
            } else{
                if(!careerDto.emptyCareer()){
                    Career career = careerDto.createCareer();
                    career.setMember(member);
                    careerRepository.save(career);
                }
            }

        }

        //자격증 수정
        for (int i = 0; i < certificateDtoList.size(); i++) {
            CertificateDto certificateDto = certificateDtoList.get(i);
            if(certificateIds.get(i) != -1){
                if(!certificateDto.emptyCertificate()){
                    Certificate certificate = certificateRepository.findById(certificateIds.get(i)).orElseThrow();
                    certificate.updateCertificate(certificateDto);
                }else {
                    certificateRepository.deleteById(certificateIds.get(i));
                }
            } else{
                if (!certificateDto.emptyCertificate()) {
                    Certificate certificate = certificateDto.createCertificate();
                    certificate.setMember(member);
                    certificateRepository.save(certificate);
                }
            }

        }

        //상훈 수정
        for (int i = 0; i < awardDtoList.size(); i++) {
            AwardDto awardDto = awardDtoList.get(i);
            if(awardIds.get(i) != -1){
                if(!awardDto.emptyAward()){
                    Award award = awardRepository.findById(awardIds.get(i)).orElseThrow();
                    award.updateAward(awardDto);
                }else {
                    awardRepository.deleteById(awardIds.get(i));
                }
            } else{
                if (!awardDto.emptyAward()) {
                    Award award = awardDto.createAward();
                    award.setMember(member);
                    awardRepository.save(award);
                }
            }

        }

        //어학 수정
        for (int i = 0; i < languageDtoList.size(); i++) {
            LanguageDto languageDto = languageDtoList.get(i);
            if(languageIds.get(i) != -1){
                if(!languageDto.emptyLanguage()){
                    Language language = languageRepository.findById(languageIds.get(i)).orElseThrow();
                    language.updateLanguage(languageDto);
                }else {
                    languageRepository.deleteById(languageIds.get(i));
                }
            } else{
                if (!languageDto.emptyLanguage()) {
                    Language language = languageDto.createLanguage();
                    language.setMember(member);
                    languageRepository.save(language);
                }
            }

        }

    }

    public SpecificationFormDto getSpecificationDetail(){

        //사용자 정보 얻어오기
        Member member = memberService.getMember();

        List<Education> educationList = educationRepository.findByMemberOrderByIdAsc(member);
        List<Career> careerList = careerRepository.findByMemberOrderByIdAsc(member);
        List<Certificate> certificateList = certificateRepository.findByMemberOrderByIdAsc(member);
        List<Award> awardList = awardRepository.findByMemberOrderByIdAsc(member);
        List<Language> languageList = languageRepository.findByMemberOrderByIdAsc(member);

        if (educationList.isEmpty() && careerList.isEmpty() && certificateList.isEmpty() && awardList.isEmpty() && languageList.isEmpty()) {
            System.out.println("빈 공간");

        }

        List<EducationDto> educationDtoList = new ArrayList<>();
        List<CareerDto> careerDtoList = new ArrayList<>();
        List<CertificateDto> certificateDtoList = new ArrayList<>();
        List<AwardDto> awardDtoList = new ArrayList<>();
        List<LanguageDto> languageDtoList = new ArrayList<>();

        for (Education education : educationList) {
            EducationDto educationDto = EducationDto.entityToDto(education);
            educationDtoList.add(educationDto);
        }

        for (Career career : careerList) {
            CareerDto careerDto = CareerDto.entityToDto(career);
            careerDtoList.add(careerDto);
        }

        for (Certificate certificate : certificateList) {
            CertificateDto certificateDto = CertificateDto.entityToDto(certificate);
            certificateDtoList.add(certificateDto);
        }

        for (Award award : awardList) {
            AwardDto awardDto = AwardDto.entityToDto(award);
            awardDtoList.add(awardDto);
        }

        for (Language language : languageList) {
            LanguageDto languageDto = LanguageDto.entityToDto(language);
            languageDtoList.add(languageDto);
        }

        SpecificationFormDto specificationFormDto = new SpecificationFormDto();
        specificationFormDto.setEducationDtoList(educationDtoList);
        specificationFormDto.setCareerDtoList(careerDtoList);
        specificationFormDto.setCertificateDtoList(certificateDtoList);
        specificationFormDto.setAwardDtoList(awardDtoList);
        specificationFormDto.setLanguageDtoList(languageDtoList);

        return specificationFormDto;

    }









}
