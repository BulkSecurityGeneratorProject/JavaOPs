package ru.javawebinar.jweb.repository;

import ru.javawebinar.jweb.domain.Lesson;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Lesson entity.
 */
public interface LessonRepository extends JpaRepository<Lesson,Long> {

}
