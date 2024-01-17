package inhatc.spring.resume.dto;

import inhatc.spring.resume.entity.Award;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardDto {


    private Long id;

    private String name;

    private String rating;

    private String organization;

    private String awarddate;

    private String detail;

    private static ModelMapper modelMapper = new ModelMapper();

    public Award createAward(){
        return modelMapper.map(this, Award.class);
    }

    public static AwardDto entityToDto(Award award){
        return modelMapper.map(award, AwardDto.class);
    }

    public Boolean emptyAward(){
        if(this.name == null && this.rating == null && this.organization == null && this.awarddate == null && this.detail == null){
            return true;
        }else {
            return false;
        }
    }
}
