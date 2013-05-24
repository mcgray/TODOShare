package ua.com.mcgray.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author orezchykov
 * @since 13.04.13
 */

@Entity
@Table(name = "todo")
public class ToDo extends BaseEntity {

    @JsonIgnore
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "todoshare_account_id", nullable = false)
    private ToDoShareAccount createdBy;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "todolist_id")
    private ToDoList list;

    private boolean done;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime dueDate;

    @NotNull
    @Length(min = 1, max = 500)
    private String title;

    @Lob
    private String note;

    public ToDoShareAccount getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final ToDoShareAccount createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }

    public DateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(final DateTime dueDate) {
        this.dueDate = dueDate;
    }

    public ToDoList getList() {
        return list;
    }

    public void setList(final ToDoList list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.done)
                .append(this.dueDate)
                .append(this.title)
                .append(this.note).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ToDo other = (ToDo) obj;
        return new EqualsBuilder().appendSuper(super.equals(obj))
                .append(this.done, other.done)
                .append(this.dueDate, other.dueDate)
                .append(this.title, other.title)
                .append(this.note, other.note).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("done", this.done)
                .append("dueDate", this.dueDate)
                .append("title", this.title)
                .append("note", this.note).toString();
    }

}
