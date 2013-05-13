package ua.com.mcgray.dto;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.springframework.core.style.ToStringCreator;

/**
 * @author orezchykov
 * @since 15.04.13
 */
public class UserForm {

    private Long id;

    @NotNull
    @Email
    private String emailAddress;

    @NotNull
    private String password;

    @NotNull
    private String repeatPassword;

    private Long toDoShareAccountId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(final String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Long getToDoShareAccountId() {
        return toDoShareAccountId;
    }

    public void setToDoShareAccountId(final Long toDoShareAccountId) {
        this.toDoShareAccountId = toDoShareAccountId;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.emailAddress)
                .append(this.password)
                .append(this.repeatPassword)
                .append(this.toDoShareAccountId).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserForm other = (UserForm) obj;
        return new EqualsBuilder()
                .append(this.id, other.id)
                .append(this.emailAddress, other.emailAddress)
                .append(this.password, other.password)
                .append(this.repeatPassword, other.repeatPassword)
                .append(this.toDoShareAccountId, other.toDoShareAccountId).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append(this.emailAddress)
                .append(this.password)
                .append(this.repeatPassword)
                .append(this.toDoShareAccountId).toString();
    }

}
