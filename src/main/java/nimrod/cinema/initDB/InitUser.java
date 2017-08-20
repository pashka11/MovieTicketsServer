package nimrod.cinema.initDB;

import nimrod.cinema.dao.CRUD;
import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.Screening;
import nimrod.cinema.objects.User;

import java.util.ArrayList;

public class InitUser
{
    public static ArrayList<User> AddUser()
    {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("admin", "admin"));
        users.add(new User("PASHA", "PASHA"));
        users.add(new User("NIMROD", "NIMROD"));

        CRUD<User> crud = new DataAccessObject<>(User.class);
        crud.DeleteAll();
        crud.CreateMany(users);

        return users;
    }
}
