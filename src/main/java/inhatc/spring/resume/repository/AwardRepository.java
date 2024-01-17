package inhatc.spring.resume.repository;

import inhatc.spring.resume.entity.Award;
import inhatc.spring.resume.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface AwardRepository   extends JpaRepository<Award, Long>, QuerydslPredicateExecutor<Award> {

    List<Award> findByMemberOrderByIdAsc(Member member);

}
