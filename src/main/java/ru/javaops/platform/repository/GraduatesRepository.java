package ru.javaops.platform.repository;

import ru.javaops.platform.domain.Graduates;
import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the Graduates entity.
 */
public interface GraduatesRepository extends JpaRepository<Graduates,Long> {

}
