package nimrodpasha.cinema.initDB;

import nimrodpasha.cinema.dao.Crud;
import nimrodpasha.cinema.dao.UserDao;
import nimrodpasha.cinema.objects.User;

import java.util.ArrayList;

/**
 * Created by Yuval on 16-Mar-17.
 * this class is for initialize the user collection
 */
public class InitUser {
    //this is an insert of default users
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();

        users.add(new User("user", "password", false));
        users.add(new User("yuval", "pass", false));
        users.add(new User("shovel", "qwe", false));
        users.add(new User("mike", "ppp", false));
        users.add(new User("ketty", "LOL", false));
        users.add(new User("admin", "admin", true));

        Crud crud = new UserDao();
        crud.dropAll();

        users.parallelStream()
                .forEach(user -> crud.create(user));

    }
}
