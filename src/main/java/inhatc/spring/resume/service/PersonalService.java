package inhatc.spring.resume.service;

import inhatc.spring.resume.dto.PersonalDto;
import inhatc.spring.resume.dto.PersonalFormDto;
import inhatc.spring.resume.dto.PersonalImgDto;
import inhatc.spring.resume.entity.Member;
import inhatc.spring.resume.entity.Personal;
import inhatc.spring.resume.entity.PersonalImg;
import inhatc.spring.resume.repository.PersonalImgRepository;
import inhatc.spring.resume.repository.PersonalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonalService {

    private final PersonalRepository personalRepository;
    private final PersonalImgRepository personalImgRepository;
    private final PersonalImgService personalImgService;
    private final MemberService memberService;

    public Long savePersonal(PersonalFormDto personalFormDto, MultipartFile personalImgFile) throws IOException {

        //사용자 정보 얻어오기
        Member member = memberService.getMember();

        // 상품 등록
        Personal personal = personalFormDto.createPersonal();
        personal.setMember(member);
        personalRepository.save(personal);

        //이미지 등록
        PersonalImg personalImg = new PersonalImg();
        personalImg.setPersonal(personal);

        personalImgService.savePersonalImg(personalImg, personalImgFile);


        return personal.getId();
    }

    public Long updatePersonal(PersonalFormDto personalFormDto, MultipartFile personalImgFile) throws IOException {
        Personal personal = personalRepository.findById(personalFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        // 더티 체킹(dirty checking)
        personal.updatePersonal(personalFormDto);
        Long personalImgId = personalFormDto.getPersonalImgId();

        personalImgService.updatePersonalImg(personalImgId, personalImgFile);

        return personal.getId();
    }


    public PersonalFormDto getPersonalDetail(){

        //사용자 정보 얻어오기
        Member member = memberService.getMember();

        Personal personal = personalRepository.findByMember(member).orElseThrow(() ->
                new EntityNotFoundException("신상정보가 없습니다."));
        PersonalFormDto personalFormDto = PersonalFormDto.entityToDto(personal);

        PersonalImg personalImg = personalImgRepository.findByPersonal(personal).orElseThrow(() ->
                new EntityNotFoundException("증명 사진이 없습니다."));
        PersonalImgDto personalImgDto = PersonalImgDto.entityToDto(personalImg);

        personalFormDto.setPersonalImgDto(personalImgDto);

        return personalFormDto;
    }
}
