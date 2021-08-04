import com.crud.config.Config;
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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = context.getBean(UserServiceImpl.class);

//        System.out.println(userService.loadUserByUsername("admin"));
        RoleService roleDAO = context.getBean(RoleServiceImpl.class);
//        System.out.println(roleDAO.findByName("ROLE_USER"));
//        setUp(roleDAO);
//        Role userRole = new Role("ROLE_USER");
//        Role adminRole = new Role("ROLE_ADMIN");
//        User admin = new User();
//        admin.setLogin("admin");
//        admin.setPassword("123");
//        admin.setEmail("savin.efim2@gmail.com");
//        admin.setRoles(Set.of(roleDAO.findById(1l), roleDAO.findById(2l)));
//        admin.setName("Efim");
//        userService.saveNewUser(admin);
//        for (int i = 0; i < 10; i++) {
//            User user = new User("user" + i, "email" + i + "@mail.ru");
//            userService.insert(user);
//            System.err.println(userService.findAll());
//            user.setId(1);
//            userService.deleteById(1l);
//            System.err.println(userService.findAll());
//        secondUser.setLogin("changedusername");
//        secondUser.setId(2);
//        userService.update(secondUser);
        System.err.println(userService.findAllUsers());

    }


    private static void setUp(RoleService roleDAO) {
        roleDAO.insert(new Role("ROLE_ADMIN"));
        roleDAO.insert(new Role("ROLE_USER"));
    }
}
