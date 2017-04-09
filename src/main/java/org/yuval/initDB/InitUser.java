package org.yuval.initDB;

import org.yuval.dao.UserDao;
import org.yuval.objects.User;

import java.util.ArrayList;

/**
 * Created by Yuval on 16-Mar-17.
 * this class is for initialize the user collection
 */
public class InitUser {
    //this is an insert of default users
    public static void main(String[]args){
        ArrayList<User> users = new ArrayList<>();

        users.add(new User("user","password",false));
        users.add(new User("yuval","pass",false));
        users.add(new User("shovel","qwe",false));
        users.add(new User("mike","ppp",false));
        users.add(new User("ketty","LOL",false));
        users.add(new User("admin","admin",true));

        UserDao userDao = new UserDao();
        userDao.dropAll();

        users.parallelStream()
                .forEach(user -> userDao.create(user));

    }
}
