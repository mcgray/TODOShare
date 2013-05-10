package ua.com.mcgray.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.mcgray.domain.ToDo;

/**
 * @author orezchykov
 * @since 15.04.13
 */
public class ToDoForm {

    private Long id;

    private Long listId;

    private boolean done;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime dueDate;

    @NotNull
    @Length(min = 1, max = 500)
    private String title;

    private String note;

    public ToDoForm() {

    }

    public ToDoForm(final ToDo toDo) {
        this.id = toDo.getId();
        this.listId = toDo.getList().getId();
        this.done = toDo.isDone();
        this.dueDate = toDo.getDueDate();
        this.title = toDo.getTitle();
        this.note = toDo.getNote();
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
}
