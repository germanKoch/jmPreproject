package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("germ", "koch", "ger@ma.ru", new Car("audi", 1)));
        userService.add(new User("serm", "soch", "ser@ma.ru", new Car("pudi", 2)));
        userService.add(new User("merm", "doch", "der@ma.ru", new Car("mudi", 12)));
        userService.add(new User("nerm", "loch", "ler@ma.ru", new Car("gudi", 31)));
        userService.add(new User("jerm", "moch", "mer@ma.ru", new Car("ludi", 666)));

        List<User> list = userService.listUsers();

        for (User user : list) {
            System.out.println(user);
        }

        User user = userService.userByCarSeries(31);

        System.out.println(user);

        context.close();
    }
}
