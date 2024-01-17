package inhatc.spring.resume.repository;

import inhatc.spring.resume.entity.Personal;
import inhatc.spring.resume.entity.PersonalImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalImgRepository extends JpaRepository<PersonalImg, Long> {

    Optional<PersonalImg> findByPersonal(Personal personal);
}
