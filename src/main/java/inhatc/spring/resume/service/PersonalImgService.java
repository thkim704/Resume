package inhatc.spring.resume.service;

import inhatc.spring.resume.entity.PersonalImg;
import inhatc.spring.resume.repository.PersonalImgRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonalImgService {

    @Value("${personalImgLocation}")
    private String personalImgLocation;

    private final PersonalImgRepository personalImgRepository;
    private final FileService fileService;

    public void savePersonalImg(PersonalImg personalImg, MultipartFile personalImgFile) throws IOException {
        String oriImgName = personalImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(personalImgLocation, oriImgName, personalImgFile.getBytes());
            imgUrl = "/images/personal/" +imgName;
        }

        personalImg.updateItemImg(imgName, oriImgName, imgUrl);
        personalImgRepository.save(personalImg);
    }

    public void updatePersonalImg(Long personalImgId,  MultipartFile personalImgFile)  throws IOException {
        if(!personalImgFile.isEmpty()){
            PersonalImg personalImg = personalImgRepository.findById(personalImgId).orElseThrow(EntityNotFoundException::new);

            if(!StringUtils.isEmpty(personalImg.getImgName())){
                fileService.deleteFile(personalImgLocation + "/" + personalImg.getImgName());
            }

            String oriImgName = personalImgFile.getOriginalFilename();
            String imgName = "";
            String imgUrl = "";

            if(!StringUtils.isEmpty(oriImgName)){
                imgName = fileService.uploadFile(personalImgLocation, oriImgName, personalImgFile.getBytes());
                imgUrl = "/images/personal/" +imgName;
            }

            personalImg.updateItemImg(imgName, oriImgName, imgUrl);

        }
    }

}
