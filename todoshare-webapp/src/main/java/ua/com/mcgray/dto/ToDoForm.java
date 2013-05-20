package ua.com.mcgray.dto;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.mcgray.domain.ToDo;

/**
 * @author orezchykov
 * @since 15.04.13
 */
public class ToDoForm {

    private Long id;

    private Long ownerId;

    private Long listId;

    private boolean done;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private DateTime dueDate;

    @NotNull
    @Length(min = 1, max = 500)
    private String title;

    private String note;

    public ToDoForm() {

    }

    public ToDoForm(final ToDo toDo) {
        this.id = toDo.getId();
        if (toDo.getCreatedBy() != null) {
            this.ownerId = toDo.getCreatedBy().getId();
        }
        if (toDo.getList() != null) {
            this.listId = toDo.getList().getId();
        }
        this.done = toDo.isDone();
        this.dueDate = toDo.getDueDate();
        this.title = toDo.getTitle();
        this.note = toDo.getNote();
    }

    public boolean isNew() {
        return id == null;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(final Long listId) {
        this.listId = listId;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(final Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.ownerId)
                .append(this.listId)
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
        final ToDoForm other = (ToDoForm) obj;
        return new EqualsBuilder()
                .append(this.id, other.id)
                .append(this.ownerId, other.ownerId)
                .append(this.listId, other.listId)
                .append(this.done, other.done)
                .append(this.dueDate, other.dueDate)
                .append(this.title, other.title)
                .append(this.note, other.note).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("ownerId", this.ownerId)
                .append("listId", this.listId)
                .append("done", this.done)
                .append("dueDate", this.dueDate)
                .append("title", this.title)
                .append("note", this.note).toString();
    }
}
