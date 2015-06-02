package ru.javaops.platform.repository;

import ru.javaops.platform.domain.Course;
import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the Course entity.
 */
public interface CourseRepository extends JpaRepository<Course,Long> {

}
