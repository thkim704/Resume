package inhatc.spring.resume.dto;

import inhatc.spring.resume.entity.PersonalImg;
import jakarta.persistence.Entity;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalImgDto {

    private Long id;

    private String imgName;     // 이미지 파일명
    private String oriImgName;  // 원본 이미지 파일명
    private String imgUrl;      // 이미지 파일 경로

    private static ModelMapper modelMapper = new ModelMapper();

    public static PersonalImgDto entityToDto(PersonalImg personalImg){ return modelMapper.map(personalImg, PersonalImgDto.class); }
}
