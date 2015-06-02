package ru.javaops.platform.repository;

import ru.javaops.platform.domain.Lesson;
import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the Lesson entity.
 */
public interface LessonRepository extends JpaRepository<Lesson,Long> {

}
