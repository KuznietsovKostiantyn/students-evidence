package my.application;

import my.application.configuration.WebConfig;
import my.application.configuration.WebInitializer;
import my.application.model.Student;
import my.application.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInitializer.class, WebConfig.class})
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentServiceImplTest {


    @Autowired
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void preConfiguration() throws Exception{
        studentService.save(new Student("John", "Walker", 21, "J180698" ));
        studentService.save(new Student("Peter", "Burns", 49, "P289384" ));
    }

    @Test
    @Transactional
    public void studentsTest() throws Exception{

        mockMvc.perform(get("/rest/students")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[0].surname", is("Walker")))
                .andExpect(jsonPath("$[1].name", is("Peter")))
                .andExpect(jsonPath("$[1].surname", is("Burns")));

        mockMvc.perform(delete("/rest/student/1")).andExpect(status().isOk());

        mockMvc.perform(get("/rest/findBySurname/Burns").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Peter")));

    }
}
