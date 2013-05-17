package ua.com.mcgray.domain;

import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "todo_list")
public class ToDoList extends BaseEntity {

    @NotNull
    @Length(min = 3, max = 255)
    private String title;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "todoshare_account_id", nullable = false)
    private ToDoShareAccount owner;

    @ManyToMany
    @JoinTable(name = "list_members", joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "todoshare_account_id", referencedColumnName = "id"))
    private Set<ToDoShareAccount> members;

    @OneToMany(mappedBy = "list")
    private List<ToDo> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public ToDoShareAccount getOwner() {
        return owner;
    }

    public void setOwner(final ToDoShareAccount owner) {
        this.owner = owner;
    }

    public Set<ToDoShareAccount> getMembers() {
        return members;
    }

    public void setMembers(final Set<ToDoShareAccount> members) {
        this.members = members;
    }

    public List<ToDo> getList() {
        return list;
    }

    public void setList(final List<ToDo> list) {
        this.list = list;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.title).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ToDoList other = (ToDoList) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.title, other.title).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("title", this.title).toString();
    }
}
