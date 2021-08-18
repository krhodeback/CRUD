package com.crud.config;

import com.crud.config.handler.LoginSuccessHandler;
import com.crud.entity.Role;
import com.crud.entity.User;
import com.crud.service.RoleService;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Set;

@Configuration
@EnableWebSecurity
@Component
@ComponentScan(basePackageClasses = LoginSuccessHandler.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoginSuccessHandler successHandler;
    private final UserDetailsService userDetailsService;
    private final RoleService roleService;

    @Autowired
    public SecurityConfig(LoginSuccessHandler successHandler, UserDetailsService userDetailsService, RoleService roleService) {
        this.successHandler = successHandler;
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
        rolesSetUp();
        adminSetUp();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and().formLogin()
                .successHandler(successHandler)
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll();

        http.logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/crud/logout"))
                .and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/user").access("hasAnyRole('USER', 'ADMIN')")
                .anyRequest().authenticated();
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void adminSetUp() {
        if (userDetailsService.loadUserByUsername("admin") == null) {
            User admin = new com.crud.entity.User();
            admin.setName("adminname");
            admin.setEmail("admin@mail.ru");
            admin.setLogin("admin");
            admin.setPassword("123");
            admin.setRoles(Set.of(roleService.findByName("ROLE_ADMIN"), roleService.findByName("ROLE_USER")));
            UserService userService = (UserService) userDetailsService;
            userService.saveNewUser(admin);
        }
    }

    private void rolesSetUp() {
        if ((roleService.findByName("ROLE_ADMIN") == null) && (roleService.findByName("ROLE_USER") == null)) {
            roleService.insert(new Role("ROLE_ADMIN"));
            roleService.insert(new Role("ROLE_USER"));
        }
    }
}
