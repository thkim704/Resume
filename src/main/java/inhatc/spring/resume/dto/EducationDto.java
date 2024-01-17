package inhatc.spring.resume.dto;

import inhatc.spring.resume.entity.Education;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDto {

    private Long id;

    private String name;

    private String department;

    private String admissiondate;

    private String graduationdate;

    private static ModelMapper modelMapper = new ModelMapper();

    public Education createEducation(){
        return modelMapper.map(this, Education.class);
    }

    public static EducationDto entityToDto(Education education){
        return modelMapper.map(education, EducationDto.class);
    }

    public Boolean emptyEducation(){
        if(this.name == null && this.admissiondate == null && this.department == null && this.graduationdate == null){
            return true;
        }else {
            return false;
        }
    }
}
