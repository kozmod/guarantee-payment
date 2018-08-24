package ru.aora.microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.aora.microservice.entity.user.UserRole;
import ru.aora.microservice.service.UserService;

import static ru.aora.microservice.controller.UserController.UPDATE_OR_CREATE_MAPPING;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(UPDATE_OR_CREATE_MAPPING).hasAnyAuthority(UserRole.ADMIN.getAuthority())
                .antMatchers("/**").hasAnyAuthority(UserRole.USER.getAuthority(), UserRole.ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll().defaultSuccessUrl("/")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    }

    //    public void antMatch(HttpSecurity http) throws Exception {
//        Properties prop = new Properties();
//        prop.load(this.getClass().getClassLoader().getResourceAsStream("urlRolesAccess.properties"));
//        for (Object value : prop.values()) {
//            http.authorizeRequests()
//                    .antMatchers(value.toString())
//                    .hasAnyAuthority(
//                            prop.getProperty(value.toString())
//                    );
//
//
//        }
//    }
}