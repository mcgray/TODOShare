package ua.com.mcgray.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @author orezchykov
 * @since 15.04.13
 */
public class ToDoListForm {

    private Long id;

    @NotNull
    @Length(min = 3, max = 255)
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
