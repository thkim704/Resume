package inhatc.spring.resume.repository;

import inhatc.spring.resume.entity.Certificate;
import inhatc.spring.resume.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long>, QuerydslPredicateExecutor<Certificate> {

    List<Certificate> findByMemberOrderByIdAsc(Member member);

}
