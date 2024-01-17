package inhatc.spring.resume.entity;

import inhatc.spring.resume.constant.GenderStatus;
import inhatc.spring.resume.constant.MarriedStatus;
import inhatc.spring.resume.constant.MilitaryStatus;
import inhatc.spring.resume.dto.PersonalFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String phone;

    private String tel;

    private String fax;

    private String zipcode;

    private String address;

    private String birth;

    @Enumerated(EnumType.STRING)
    private GenderStatus genderStatus;

    @Enumerated(EnumType.STRING)
    private MarriedStatus marriedStatus;

    @Enumerated(EnumType.STRING)
    private MilitaryStatus militaryStatus;

    public void updatePersonal(PersonalFormDto personalFormDto){
        this.phone = personalFormDto.getPhone();
        this.tel = personalFormDto.getTel();
        this.fax = personalFormDto.getFax();
        this.zipcode = personalFormDto.getZipcode();
        this.address = personalFormDto.getAddress();
        this.birth = personalFormDto.getBirth();
        this.genderStatus = personalFormDto.getGenderStatus();
        this.marriedStatus = personalFormDto.getMarriedStatus();
        this.militaryStatus = personalFormDto.getMilitaryStatus();
    }


}
