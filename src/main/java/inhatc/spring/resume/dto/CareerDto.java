package inhatc.spring.resume.dto;

import inhatc.spring.resume.entity.Career;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerDto {

    private Long id;

    private String name;

    private String position;

    private String startdate;

    private String enddate;

    private String detail;

    private static ModelMapper modelMapper = new ModelMapper();

    public Career createCareer(){
        return modelMapper.map(this, Career.class);
    }

    public static CareerDto entityToDto(Career career){
        return modelMapper.map(career, CareerDto.class);
    }

    public Boolean emptyCareer(){
        if(this.name == null && this.position == null && this.startdate == null && this.enddate == null && this.detail == null){
            return true;
        }else {
            return false;
        }
    }
}
