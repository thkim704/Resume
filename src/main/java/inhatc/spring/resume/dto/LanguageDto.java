package inhatc.spring.resume.dto;

import inhatc.spring.resume.entity.Language;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageDto {

    private Long id;

    private String name;

    private Integer score;

    private String acquisitiondate;

    private static ModelMapper modelMapper = new ModelMapper();

    public Language createLanguage(){
        return modelMapper.map(this, Language.class);
    }

    public static LanguageDto entityToDto(Language language){
        return modelMapper.map(language, LanguageDto.class);
    }

    public Boolean emptyLanguage(){
        if(this.name == null && this.score == null && this.acquisitiondate == null){
            return true;
        }else {
            return false;
        }
    }
}
