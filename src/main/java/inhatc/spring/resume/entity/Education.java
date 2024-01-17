package inhatc.spring.resume.entity;

import inhatc.spring.resume.dto.EducationDto;
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
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "education_name")
    private String name;

    private String department;

    private String admissiondate;

    private String graduationdate;

    public void updateEducation(EducationDto educationDto){
        this.name = educationDto.getName();
        this.department = educationDto.getDepartment();
        this.admissiondate = educationDto.getAdmissiondate();
        this.graduationdate = educationDto.getGraduationdate();
    }
}
