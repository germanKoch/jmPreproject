package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();

        service.createUsersTable();
        for (int i = 0; i < 4; i++) {
            service.saveUser("germ" + i, "germ" + i, (byte) 12);
            System.out.println("User с именем – germ" + i + " добавлен в базу данных");
        }

        List<User> list = service.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
