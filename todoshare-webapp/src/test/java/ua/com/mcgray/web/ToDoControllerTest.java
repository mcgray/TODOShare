package ua.com.mcgray.web;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.mcgray.dto.ToDoForm;
import ua.com.mcgray.service.ToDoService;

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
    private ToDoService toDoService;

    @InjectMocks
    private ToDoController toDoController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(toDoController).build();
    }

    @Test
    public void testDashboard() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }

    @Test
    public void testToDoList() throws Exception {
        when(toDoService.getToDos()).thenReturn(new ArrayList<ToDoForm>());
        mockMvc.perform(get("/todo/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("todo-list"));
        verify(toDoService).getToDos();
    }

}
