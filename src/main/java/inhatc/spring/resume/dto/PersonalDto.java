package inhatc.spring.resume.dto;

import inhatc.spring.resume.constant.GenderStatus;
import inhatc.spring.resume.constant.MarriedStatus;
import inhatc.spring.resume.constant.MilitaryStatus;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDto {

    private Long id;

    private String phone;

    private String tel;

    private String fax;

    private String zipcode;

    private String address;

    private String birth;

    private GenderStatus genderStatus;

    private MarriedStatus marriedStatus;

    private MilitaryStatus militaryStatus;
}
