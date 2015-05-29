package ru.javawebinar.jweb.web.rest;

import com.codahale.metrics.annotation.Timed;
import ru.javawebinar.jweb.domain.Lesson;
import ru.javawebinar.jweb.repository.LessonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Lesson.
 */
@RestController
@RequestMapping("/api")
public class LessonResource {

    private final Logger log = LoggerFactory.getLogger(LessonResource.class);

    @Inject
    private LessonRepository lessonRepository;

    /**
     * POST  /lessons -> Create a new lesson.
     */
    @RequestMapping(value = "/lessons",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@Valid @RequestBody Lesson lesson) throws URISyntaxException {
        log.debug("REST request to save Lesson : {}", lesson);
        if (lesson.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new lesson cannot already have an ID").build();
        }
        lessonRepository.save(lesson);
        return ResponseEntity.created(new URI("/api/lessons/" + lesson.getId())).build();
    }

    /**
     * PUT  /lessons -> Updates an existing lesson.
     */
    @RequestMapping(value = "/lessons",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@Valid @RequestBody Lesson lesson) throws URISyntaxException {
        log.debug("REST request to update Lesson : {}", lesson);
        if (lesson.getId() == null) {
            return create(lesson);
        }
        lessonRepository.save(lesson);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /lessons -> get all the lessons.
     */
    @RequestMapping(value = "/lessons",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Lesson> getAll() {
        log.debug("REST request to get all Lessons");
        return lessonRepository.findAll();
    }

    /**
     * GET  /lessons/:id -> get the "id" lesson.
     */
    @RequestMapping(value = "/lessons/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Lesson> get(@PathVariable Long id) {
        log.debug("REST request to get Lesson : {}", id);
        return Optional.ofNullable(lessonRepository.findOne(id))
            .map(lesson -> new ResponseEntity<>(
                lesson,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /lessons/:id -> delete the "id" lesson.
     */
    @RequestMapping(value = "/lessons/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Lesson : {}", id);
        lessonRepository.delete(id);
    }
}
