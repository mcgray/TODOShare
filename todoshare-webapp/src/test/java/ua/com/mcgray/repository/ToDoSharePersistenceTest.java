package ua.com.mcgray.repository;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.domain.ToDoList;
import ua.com.mcgray.domain.ToDoShareAccount;
import ua.com.mcgray.domain.User;
import ua.com.mcgray.repository.jpa.ToDoListRepository;
import ua.com.mcgray.repository.jpa.ToDoRepository;
import ua.com.mcgray.repository.jpa.ToDoShareAccountRepository;
import ua.com.mcgray.repository.jpa.UserRepository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author orezchykov
 * @since 15.04.13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-persistence.xml")
@Transactional
public class ToDoSharePersistenceTest {

    public static final long ADMIN_USER_ID = 2l;
    public static final String ADMIN_SHARED_LIST_TITLE = "Admin shared list";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoShareAccountRepository toDoShareAccountRepository;

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Test
    public void testThatUserAccountCanBeFound() {
        List<User> users = userRepository.findAll();
        assertThat(users, notNullValue());
        assertThat(users.size(), is(2));

    }

    @Test
    public void testThatToDoShareAccountHasListsAndToDos() throws Exception {
        User user = userRepository.findOne(ADMIN_USER_ID);
        ToDoShareAccount toDoShareAccount = user.getToDoShareAccount();

        Set<ToDoList> toDoLists = toDoShareAccount.getToDoLists();
        assertThat(toDoLists.size(), is(2));

        Set<ToDo> toDos = toDoShareAccount.getToDos();
        assertThat(toDos.size(), is(2));

        for (ToDoList toDoList : toDoLists) {
            if (toDoList.getTitle().equals(ADMIN_SHARED_LIST_TITLE)) {
                assertThat(toDoList.getMembers().size(), is(1));
            }
        }


    }
}
