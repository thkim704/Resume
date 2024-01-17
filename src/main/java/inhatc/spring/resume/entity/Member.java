package inhatc.spring.resume.entity;

import inhatc.spring.resume.constant.Role;
import inhatc.spring.resume.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @Column(name = "member_id", unique = true)
    private String id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .role(Role.USER)
                .id(memberFormDto.getId())
                .email(memberFormDto.getEmail())
                .name(memberFormDto.getName())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .build();

        return member;

    }

    public void updateMember(MemberFormDto memberFormDto){
        this.name = memberFormDto.getName();
        this.email = memberFormDto.getEmail();
    }
}
