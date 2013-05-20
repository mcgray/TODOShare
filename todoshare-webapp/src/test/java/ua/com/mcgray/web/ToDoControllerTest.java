package ua.com.mcgray.web;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.mcgray.domain.ToDoShareAccount;
import ua.com.mcgray.domain.User;
import ua.com.mcgray.dto.ToDoForm;
import ua.com.mcgray.service.ToDoServiceImpl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author orezchykov
 * @since 10.05.13
 */
@RunWith(MockitoJUnitRunner.class)
public class ToDoControllerTest {

    @Mock
    private ToDoServiceImpl toDoServiceImpl;

    @InjectMocks
    private ToDoController toDoController;

    private MockMvc mockMvc;
    private User user;
    private UsernamePasswordAuthenticationToken authentication;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(toDoController).build();
        user = new User();
        user.setToDoShareAccount(new ToDoShareAccount());
        authentication = new UsernamePasswordAuthenticationToken(user, new Object());
    }

    @Test
    public void testDashboard() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }

    @Test
    public void testToDoList() throws Exception {
        when(toDoServiceImpl.getToDos(user.getToDoShareAccount())).thenReturn(new ArrayList<ToDoForm>());
        mockMvc.perform(get("/todo/list").principal(authentication))
                .andExpect(status().isOk())
                .andExpect(view().name("todo-list"));
        verify(toDoServiceImpl).getToDos(user.getToDoShareAccount());
    }
}
