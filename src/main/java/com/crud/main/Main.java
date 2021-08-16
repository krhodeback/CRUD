package com.crud.main;

import com.crud.config.Config;
import com.crud.config.SecurityConfig;
import com.crud.config.WebConfig;
import com.crud.controller.AdminController;
import com.crud.dao.RoleDAO;
import com.crud.dao.RoleDAOImpl;
import com.crud.dao.UserDAO;
import com.crud.dao.UserDAOImpl;
import com.crud.entity.Role;
import com.crud.entity.User;
import com.crud.service.RoleService;
import com.crud.service.RoleServiceImpl;
import com.crud.service.UserService;
import com.crud.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.Set;

@SpringBootApplication(scanBasePackages = {"com.crud.config"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
