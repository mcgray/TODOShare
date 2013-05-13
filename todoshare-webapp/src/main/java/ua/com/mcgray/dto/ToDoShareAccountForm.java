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
public class ToDoShareAccountForm {

    private Long id;

    @NotNull
    @Length(min = 6, max = 255)
    private String nickname;

    @NotNull
    @Length(min = 2, max = 255)
    private String firstName;

    @NotNull
    @Length(min = 2, max = 255)
    private String lastName;

    @NotNull
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.nickname)
                .append(this.firstName)
                .append(this.lastName)
                .append(this.userId).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ToDoShareAccountForm other = (ToDoShareAccountForm) obj;
        return new EqualsBuilder()
                .append(this.id, other.id)
                .append(this.nickname, other.nickname)
                .append(this.firstName, other.firstName)
                .append(this.lastName, other.lastName)
                .append(this.userId, other.userId).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("nickname", this.nickname)
                .append("firstName", this.firstName)
                .append("lastName", this.lastName)
                .append("userId", this.userId).toString();
    }
}
