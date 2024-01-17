package inhatc.spring.resume.dto;

import inhatc.spring.resume.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberFormDto {
    @NotBlank(message = "이름은 필수입력 입니다.")
    private String name;

    @NotBlank(message = "아이디는 필수입력 입니다.")
    private String id;

    @NotEmpty(message = "이메일은 필수입력 입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입력 입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상 16자 이하로 입력해주세요")
    private String password;

    @NotEmpty(message = "비밀번호를 다시 입력해야합니다.")
    private String password_confirm;

    private static ModelMapper modelMapper = new ModelMapper();

    public static MemberFormDto entityToDto(Member member) { return modelMapper.map(member, MemberFormDto.class); }

}
