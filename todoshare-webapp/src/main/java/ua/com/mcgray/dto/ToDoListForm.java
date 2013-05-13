package ua.com.mcgray.dto;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.core.style.ToStringCreator;

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

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
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
        final ToDoListForm other = (ToDoListForm) obj;
        return new EqualsBuilder()
                .append(this.id, other.id)
                .append(this.title, other.title).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("title", this.title).toString();
    }
}
