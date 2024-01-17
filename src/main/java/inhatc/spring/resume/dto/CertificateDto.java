package inhatc.spring.resume.dto;

import inhatc.spring.resume.entity.Certificate;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateDto {

    private Long id;

    private String name;

    private String rating;

    private String organization;

    private String acquisitiondate;

    private static ModelMapper modelMapper = new ModelMapper();

    public Certificate createCertificate(){
        return modelMapper.map(this, Certificate.class);
    }

    public static CertificateDto entityToDto(Certificate certificate){
        return modelMapper.map(certificate, CertificateDto.class);
    }

    public Boolean emptyCertificate(){
        if(this.name == null && this.rating == null && this.organization == null && this.acquisitiondate == null){
            return true;
        }else {
            return false;
        }
    }
}
