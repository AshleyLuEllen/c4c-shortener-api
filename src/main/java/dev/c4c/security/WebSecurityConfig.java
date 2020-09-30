package dev.c4c.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select user_email, pass_hash, enabled"
                + " from users where user_email=?")
            .authoritiesByUsernameQuery("select user_email, user_role"
                + " from users where user_email=?")
            .passwordEncoder(PASSWORD_ENCODER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers("/admin/createuser").permitAll() // todo: remove
            .antMatchers("/admin/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .logout()
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .and()
            .httpBasic() // Authenticate users with HTTP basic authentication
//            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        ;
    }
}