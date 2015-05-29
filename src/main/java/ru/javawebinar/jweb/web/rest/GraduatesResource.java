package ru.javawebinar.jweb.web.rest;

import com.codahale.metrics.annotation.Timed;
import ru.javawebinar.jweb.domain.Graduates;
import ru.javawebinar.jweb.repository.GraduatesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Graduates.
 */
@RestController
@RequestMapping("/api")
public class GraduatesResource {

    private final Logger log = LoggerFactory.getLogger(GraduatesResource.class);

    @Inject
    private GraduatesRepository graduatesRepository;

    /**
     * POST  /graduatess -> Create a new graduates.
     */
    @RequestMapping(value = "/graduatess",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Graduates graduates) throws URISyntaxException {
        log.debug("REST request to save Graduates : {}", graduates);
        if (graduates.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new graduates cannot already have an ID").build();
        }
        graduatesRepository.save(graduates);
        return ResponseEntity.created(new URI("/api/graduatess/" + graduates.getId())).build();
    }

    /**
     * PUT  /graduatess -> Updates an existing graduates.
     */
    @RequestMapping(value = "/graduatess",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Graduates graduates) throws URISyntaxException {
        log.debug("REST request to update Graduates : {}", graduates);
        if (graduates.getId() == null) {
            return create(graduates);
        }
        graduatesRepository.save(graduates);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /graduatess -> get all the graduatess.
     */
    @RequestMapping(value = "/graduatess",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Graduates> getAll() {
        log.debug("REST request to get all Graduatess");
        return graduatesRepository.findAll();
    }

    /**
     * GET  /graduatess/:id -> get the "id" graduates.
     */
    @RequestMapping(value = "/graduatess/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Graduates> get(@PathVariable Long id) {
        log.debug("REST request to get Graduates : {}", id);
        return Optional.ofNullable(graduatesRepository.findOne(id))
            .map(graduates -> new ResponseEntity<>(
                graduates,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /graduatess/:id -> delete the "id" graduates.
     */
    @RequestMapping(value = "/graduatess/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Graduates : {}", id);
        graduatesRepository.delete(id);
    }
}
