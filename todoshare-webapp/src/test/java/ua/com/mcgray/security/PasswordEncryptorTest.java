package ua.com.mcgray.security;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author orezchykov
 * @since 09.05.13
 */
@Ignore
public class PasswordEncryptorTest {

    public static final String PASSWORD = "123456789";
    public static final String ENCODED_PASSWORD = "$2a$10$6.p2FiKeFjJMLpGV3VhDquo1TnN0nOVGklYeUbWV1J/qHkJs3qFrS";

    public static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testEncryptPassword() throws Exception {
        String encodedPassword = bCryptPasswordEncoder.encode(PASSWORD);
        System.out.println(encodedPassword);
        assertThat(bCryptPasswordEncoder.matches(PASSWORD, ENCODED_PASSWORD), is(true));
    }
}