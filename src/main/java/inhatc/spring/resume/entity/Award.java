package inhatc.spring.resume.entity;

import inhatc.spring.resume.dto.AwardDto;
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
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "award_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "award_name")
    private String name;

    private String rating;

    private String organization;

    private String awarddate;

    private String detail;

    public void updateAward(AwardDto awardDto){
        this.name = awardDto.getName();
        this.rating = awardDto.getRating();
        this.organization = awardDto.getOrganization();
        this.awarddate = awardDto.getAwarddate();
        this.detail = awardDto.getDetail();
    }
}
