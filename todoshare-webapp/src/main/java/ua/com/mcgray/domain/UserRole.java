package ua.com.mcgray.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author orezchykov
 * @since 15.04.13
 */
public enum  UserRole implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
