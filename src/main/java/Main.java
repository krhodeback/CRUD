import com.crud.config.Config;
import com.crud.dao.UserDAO;
import com.crud.dao.UserDAOImpl;
import com.crud.entity.User;
import com.crud.service.UserService;
import com.crud.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = context.getBean(UserServiceImpl.class);
        User user = new User();
        user.setLogin("efim");
        user.setEmail("savin.efim2@gmail.com");
        userService.insert(user);
        User secondUser = new User();
        secondUser.setLogin("seconduser");
        secondUser.setEmail("email@mail.com");
        userService.insert(secondUser);
        System.err.println(userService.findAll());
        user.setId(1);
        userService.deleteById(1l);
        System.err.println(userService.findAll());
        secondUser.setLogin("changedusername");
        secondUser.setId(2);
        userService.update(secondUser);
        System.err.println(userService.findAll());


    }
}
