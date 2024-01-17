package inhatc.spring.resume.dto;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecificationFormDto {

    private List<AwardDto> awardDtoList = new ArrayList<>();

    private List<Long> awardIds = new ArrayList<>();

    private List<CareerDto> careerDtoList = new ArrayList<>();

    private List<Long> careerIds = new ArrayList<>();

    private List<CertificateDto> certificateDtoList = new ArrayList<>();

    private List<Long> certificateIds = new ArrayList<>();

    private List<EducationDto> educationDtoList = new ArrayList<>();

    private List<Long> educationIds = new ArrayList<>();

    private List<LanguageDto> languageDtoList = new ArrayList<>();

    private List<Long> languageIds = new ArrayList<>();


    private static ModelMapper modelMapper = new ModelMapper();


}
