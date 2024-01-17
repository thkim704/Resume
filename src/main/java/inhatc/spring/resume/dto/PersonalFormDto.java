package inhatc.spring.resume.dto;

import inhatc.spring.resume.constant.GenderStatus;
import inhatc.spring.resume.constant.MarriedStatus;
import inhatc.spring.resume.constant.MilitaryStatus;
import inhatc.spring.resume.entity.Personal;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalFormDto {

    private Long id;

    private String phone;

    private String tel;

    private String fax;

    private String zipcode;

    private String address;

    private String birth;

    private GenderStatus genderStatus;

    private MarriedStatus marriedStatus;

    private MilitaryStatus militaryStatus;

    private PersonalImgDto personalImgDto;

    private Long personalImgId;


    private static ModelMapper modelMapper = new ModelMapper();

    public Personal createPersonal(){
        return modelMapper.map(this, Personal.class);
    }

    public static PersonalFormDto entityToDto(Personal personal){ return modelMapper.map(personal, PersonalFormDto.class); }



}
