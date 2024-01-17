package inhatc.spring.resume.entity;

import inhatc.spring.resume.dto.CertificateDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "certificate_name")
    private String name;

    private String rating;

    private String organization;

    private String acquisitiondate;

    public void updateCertificate(CertificateDto certificateDto){
        this.name = certificateDto.getName();
        this.rating = certificateDto.getRating();
        this.organization = certificateDto.getOrganization();
        this.acquisitiondate = certificateDto.getAcquisitiondate();
    }
}
