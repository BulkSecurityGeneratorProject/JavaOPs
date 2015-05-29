package ru.javawebinar.jweb.repository;

import ru.javawebinar.jweb.domain.Graduates;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Graduates entity.
 */
public interface GraduatesRepository extends JpaRepository<Graduates,Long> {

}
