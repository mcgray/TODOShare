package ua.com.mcgray.web;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.repository.ToDoRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author orezchykov
 * @since 10.05.13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:test-web-context.xml")
@ActiveProfiles("mocks")
// Enable if services is used instead of mocks
// @Transactional
public class ControllerTest {

    @Resource
    private ToDoRepository toDoRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testDashboard() throws Exception {
		mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
	}

    @Test
    public void testViewProfile() throws Exception {
        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"));

    }

    //TODO: Fix this test
    @Test
    public void testToDoEdit() throws Exception {
        when(toDoRepository.findOne(12L)).thenReturn(new ToDo());
        mockMvc.perform(get("/todo/12")
                .param("id","12")
                .param("title", "Some title")
                .param("note","Some note")
                .param("dueDate", "20-05-2013"))
                .andExpect(status().isOk())
                .andExpect(view().name("todo-edit"));
        verify(toDoRepository).findOne(12L);

    }
}
