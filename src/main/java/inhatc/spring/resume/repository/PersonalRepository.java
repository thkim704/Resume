package inhatc.spring.resume.repository;

import inhatc.spring.resume.entity.Member;
import inhatc.spring.resume.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface PersonalRepository  extends JpaRepository<Personal, Long>, QuerydslPredicateExecutor<Personal> {

    Optional<Personal> findByMember(Member member);

}
