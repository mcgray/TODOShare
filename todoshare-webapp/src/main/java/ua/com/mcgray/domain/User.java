package ua.com.mcgray.domain;

import java.util.Collection;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.springframework.core.style.ToStringCreator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author orezchykov
 * @since 13.04.13
 */

@Entity
@Table(name = "user")
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 4752412191180798770L;

    @NotNull
    @Email
    @Column(name = "email_address")
    private String emailAddress;

    @NotNull
    private String password;

    @OneToOne
    @JoinColumn(name = "todoshare_account_id")
    private ToDoShareAccount toDoShareAccount;

    private boolean active;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user"))
    @Column(name = "authorities")
    private Set<UserRole> authorities;

    public void addAuthority(UserRole authority) {
        this.authorities.add(authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public ToDoShareAccount getToDoShareAccount() {
        return toDoShareAccount;
    }

    public void setToDoShareAccount(final ToDoShareAccount toDoShareAccount) {
        this.toDoShareAccount = toDoShareAccount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.emailAddress)
                .append(this.password)
                .append(this.active).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.emailAddress, other.emailAddress)
                .append(this.password, other.password)
                .append(this.active, other.active).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("emailAddress", this.emailAddress)
                .append("password", this.password)
                .append("active", this.active).toString();
    }
}
