package inhatc.spring.resume.repository;

import inhatc.spring.resume.entity.Career;
import inhatc.spring.resume.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CareerRepository extends JpaRepository<Career, Long>, QuerydslPredicateExecutor<Career> {

    List<Career> findByMemberOrderByIdAsc(Member member);

}
