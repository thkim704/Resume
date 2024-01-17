package inhatc.spring.resume.entity;

import inhatc.spring.resume.dto.CareerDto;
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
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "career_name")
    private String name;

    private String position;

    private String startdate;

    private String enddate;

    private String detail;

    public void updateCareer(CareerDto careerDto){
        this.name = careerDto.getName();
        this.position = careerDto.getPosition();
        this.startdate = careerDto.getStartdate();
        this.enddate = careerDto.getEnddate();
        this.detail = careerDto.getDetail();
    }
}
