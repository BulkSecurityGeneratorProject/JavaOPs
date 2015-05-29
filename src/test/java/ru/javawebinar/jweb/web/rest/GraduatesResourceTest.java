package ru.javawebinar.jweb.web.rest;

import ru.javawebinar.jweb.Application;
import ru.javawebinar.jweb.domain.Graduates;
import ru.javawebinar.jweb.repository.GraduatesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GraduatesResource REST controller.
 *
 * @see GraduatesResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class GraduatesResourceTest {


    private static final Integer DEFAULT_GRADUATES_ORDER = 0;
    private static final Integer UPDATED_GRADUATES_ORDER = 1;

    @Inject
    private GraduatesRepository graduatesRepository;

    private MockMvc restGraduatesMockMvc;

    private Graduates graduates;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GraduatesResource graduatesResource = new GraduatesResource();
        ReflectionTestUtils.setField(graduatesResource, "graduatesRepository", graduatesRepository);
        this.restGraduatesMockMvc = MockMvcBuilders.standaloneSetup(graduatesResource).build();
    }

    @Before
    public void initTest() {
        graduates = new Graduates();
        graduates.setGraduatesOrder(DEFAULT_GRADUATES_ORDER);
    }

    @Test
    @Transactional
    public void createGraduates() throws Exception {
        int databaseSizeBeforeCreate = graduatesRepository.findAll().size();

        // Create the Graduates
        restGraduatesMockMvc.perform(post("/api/graduatess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(graduates)))
                .andExpect(status().isCreated());

        // Validate the Graduates in the database
        List<Graduates> graduatess = graduatesRepository.findAll();
        assertThat(graduatess).hasSize(databaseSizeBeforeCreate + 1);
        Graduates testGraduates = graduatess.get(graduatess.size() - 1);
        assertThat(testGraduates.getGraduatesOrder()).isEqualTo(DEFAULT_GRADUATES_ORDER);
    }

    @Test
    @Transactional
    public void getAllGraduatess() throws Exception {
        // Initialize the database
        graduatesRepository.saveAndFlush(graduates);

        // Get all the graduatess
        restGraduatesMockMvc.perform(get("/api/graduatess"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(graduates.getId().intValue())))
                .andExpect(jsonPath("$.[*].graduates_order").value(hasItem(DEFAULT_GRADUATES_ORDER)));
    }

    @Test
    @Transactional
    public void getGraduates() throws Exception {
        // Initialize the database
        graduatesRepository.saveAndFlush(graduates);

        // Get the graduates
        restGraduatesMockMvc.perform(get("/api/graduatess/{id}", graduates.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(graduates.getId().intValue()))
            .andExpect(jsonPath("$.graduates_order").value(DEFAULT_GRADUATES_ORDER));
    }

    @Test
    @Transactional
    public void getNonExistingGraduates() throws Exception {
        // Get the graduates
        restGraduatesMockMvc.perform(get("/api/graduatess/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGraduates() throws Exception {
        // Initialize the database
        graduatesRepository.saveAndFlush(graduates);

		int databaseSizeBeforeUpdate = graduatesRepository.findAll().size();

        // Update the graduates
        graduates.setGraduatesOrder(UPDATED_GRADUATES_ORDER);
        restGraduatesMockMvc.perform(put("/api/graduatess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(graduates)))
                .andExpect(status().isOk());

        // Validate the Graduates in the database
        List<Graduates> graduatess = graduatesRepository.findAll();
        assertThat(graduatess).hasSize(databaseSizeBeforeUpdate);
        Graduates testGraduates = graduatess.get(graduatess.size() - 1);
        assertThat(testGraduates.getGraduatesOrder()).isEqualTo(UPDATED_GRADUATES_ORDER);
    }

    @Test
    @Transactional
    public void deleteGraduates() throws Exception {
        // Initialize the database
        graduatesRepository.saveAndFlush(graduates);

		int databaseSizeBeforeDelete = graduatesRepository.findAll().size();

        // Get the graduates
        restGraduatesMockMvc.perform(delete("/api/graduatess/{id}", graduates.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Graduates> graduatess = graduatesRepository.findAll();
        assertThat(graduatess).hasSize(databaseSizeBeforeDelete - 1);
    }
}
