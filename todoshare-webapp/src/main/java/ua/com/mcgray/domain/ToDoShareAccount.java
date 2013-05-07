package ua.com.mcgray.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @author orezchykov
 * @since 13.04.13
 */
@Entity
@Table(name = "todoshare_account")
public class ToDoShareAccount extends BaseEntity {

    @NotNull
    @Length(min = 6, max = 255)
    private String nickname;

    @NotNull
    @Length(min = 2, max = 255)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Length(min = 2, max = 255)
    @Column(name = "last_name")
    private String lastName;

    @OneToOne(mappedBy = "toDoShareAccount")
    private User user;

    @OneToMany(mappedBy = "createdBy")
    private Set<ToDo> toDos;

    @OneToMany(mappedBy = "owner")
    private Set<ToDoList> toDoLists;

    @ManyToMany(mappedBy = "members")
    private Set<ToDoList> lists;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Set<ToDo> getToDos() {
        return toDos;
    }

    public void setToDos(final Set<ToDo> toDos) {
        this.toDos = toDos;
    }

    public Set<ToDoList> getToDoLists() {
        return toDoLists;
    }

    public void setToDoLists(final Set<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }
}
