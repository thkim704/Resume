package inhatc.spring.resume.repository;

import inhatc.spring.resume.entity.Education;
import inhatc.spring.resume.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long>, QuerydslPredicateExecutor<Education> {

    List<Education> findByMemberOrderByIdAsc(Member member);

}
