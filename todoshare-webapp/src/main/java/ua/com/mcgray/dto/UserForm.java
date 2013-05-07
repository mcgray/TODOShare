package ua.com.mcgray.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

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
}
