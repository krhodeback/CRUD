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
for(int i = 0 ; i<10 ; i++){
    User user = new User("user" + i,"email"+i+"@mail.ru");
    userService.insert(user);
}
    //        System.err.println(userService.findAll());
//        user.setId(1);
//        userService.deleteById(1l);
//        System.err.println(userService.findAll());
//        secondUser.setLogin("changedusername");
//        secondUser.setId(2);
//        userService.update(secondUser);
//        System.err.println(userService.findAll());


    }
}
