package ua.com.mcgray.web;

import java.util.Collections;
import javax.annotation.Resource;

import com.mysema.query.types.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.domain.ToDoShareAccount;
import ua.com.mcgray.domain.User;
import ua.com.mcgray.dto.ToDoForm;
import ua.com.mcgray.repository.jpa.ToDoRepository;
import ua.com.mcgray.service.ToDoService;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

    @Resource
    private ToDoService toDoService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

    private User user;
    private UsernamePasswordAuthenticationToken authentication;

    @Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .alwaysExpect(status().isOk())
                .alwaysDo(print())
                .build();
        user = new User();
        user.setToDoShareAccount(new ToDoShareAccount());
        authentication = new UsernamePasswordAuthenticationToken(user, new Object());
	}

	@Test
	public void testDashboard() throws Exception {
		mockMvc.perform(get("/"))
                .andExpect(view().name("dashboard"));
	}

    @Test
    public void testViewProfile() throws Exception {
        mockMvc.perform(get("/profile"))
                .andExpect(view().name("profile"));

    }

    @Test
    public void testToDoEdit() throws Exception {
        when(toDoRepository.findOne(12L)).thenReturn(new ToDo());
        mockMvc.perform(get("/todo/12"))
                .andExpect(view().name("todo-edit"));
        verify(toDoRepository).findOne(12L);

    }

    @Test
    public void testListData() throws Exception {
        final Predicate predicate = ToDoController.constructPredicate(user.getToDoShareAccount());
        final Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "id"));
        when(toDoService.findAll(predicate, pageable)).thenReturn(new PageImpl<>(Collections.<ToDo>emptyList()));
        mockMvc.perform(get("/todo/listdata")
                .accept(MediaType.APPLICATION_JSON)
                .principal(authentication)
                .param("page", "0")
                .param("size", "10")
                .param("sort", "id,desc"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.totalElements").value(is(0)));

    }

    //TODO: Find out why charset is added as a Media Type
    @Test
    public void testToDoList() throws Exception {
        when(toDoService.getToDos(user.getToDoShareAccount())).thenReturn(Collections.<ToDoForm>emptyList());
        mockMvc.perform(get("/todo/list")
                .principal(authentication))
//                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("todo-list"))
                .andExpect(model().attribute("toDoForm", new ToDoForm()))
                .andExpect(model().attribute("todoList", Collections.<ToDoForm>emptyList()));


    }

    @Test
    public void testToDoUpdate() throws Exception {
        mockMvc.perform(put("/todo/12")
                .principal(authentication)
                .param("id","12")
                .param("title", "Some title")
                .param("note","Some note")
                .param("dueDate", "20-05-2013"))
                .andExpect(status().is3xxRedirection());

    }
}
