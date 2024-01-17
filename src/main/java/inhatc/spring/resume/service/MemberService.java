package inhatc.spring.resume.service;

import inhatc.spring.resume.dto.MemberFormDto;
import inhatc.spring.resume.entity.Member;
import inhatc.spring.resume.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicationMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicationMember(Member member) {
        Optional<Member> findMember = memberRepository.findById(member.getId());
        if(findMember.isPresent()){
            System.out.println(findMember.get().getName());
            throw  new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Member getMember(){

        //사용자 정보 얻어오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();
        Member member = memberRepository.findById(username)
                .orElseThrow(()-> new UsernameNotFoundException("해당 사용자가 없습니다." + username));

        return member;
    }


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Member member = memberRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("해당 사용자가 없습니다." + id));

        log.info("========[로그인 사용자] : " + member);

        return User.builder()
                .username(member.getId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    public MemberFormDto getMemberDetail(){
        Member member = getMember();
        MemberFormDto memberFormDto = MemberFormDto.entityToDto(member);
        return memberFormDto;
    }

    public void updateMember(MemberFormDto memberFormDto){
        String username = getMember().getId();
        Member member = memberRepository.findById(username)
                .orElseThrow(()-> new UsernameNotFoundException("해당 사용자가 없습니다." + username));
        member.updateMember(memberFormDto);
        System.out.println(member.getName());
    }
}
