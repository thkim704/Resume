package inhatc.spring.resume.entity;

import inhatc.spring.resume.dto.LanguageDto;
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
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "language_name")
    private String name;

    private Integer score;

    private String acquisitiondate;

    public void updateLanguage(LanguageDto languageDto){
        this.name = languageDto.getName();
        this.score = languageDto.getScore();
        this.acquisitiondate = languageDto.getAcquisitiondate();
    }
}
