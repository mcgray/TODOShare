package ua.com.mcgray.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.core.style.ToStringCreator;

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

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.nickname)
                .append(this.firstName)
                .append(this.lastName).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ToDoShareAccount other = (ToDoShareAccount) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.nickname, other.nickname)
                .append(this.firstName, other.firstName)
                .append(this.lastName, other.lastName).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("nickname",  this.nickname)
                .append("firstName",  this.firstName)
                .append("lastName",  this.lastName).toString();
    }
}
