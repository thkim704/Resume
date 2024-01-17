package inhatc.spring.resume.repository;

import inhatc.spring.resume.entity.Language;
import inhatc.spring.resume.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long>, QuerydslPredicateExecutor<Language> {

    List<Language> findByMemberOrderByIdAsc(Member member);

}
