package ua.com.mcgray.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
}
