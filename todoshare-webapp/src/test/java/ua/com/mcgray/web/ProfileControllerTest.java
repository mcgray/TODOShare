package ua.com.mcgray.web;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author orezchykov
 * @since 10.05.13
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();

    }

    @Ignore
    @Test
    public void testShowProfile() throws Exception {
        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk());
    }
}
