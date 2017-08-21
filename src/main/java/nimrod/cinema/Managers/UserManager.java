package nimrod.cinema.Managers;

import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.User;

public class UserManager {

    private DataAccessObject<User> _userDao;

    public UserManager()
    {
        _userDao = new DataAccessObject<>(User.class);
    }

    public User HandleValidateUser(User user)
    {
        User userFromDb = _userDao.ReadOne(user.getUser().toLowerCase());

        if (userFromDb == null || !userFromDb.getPassword().equals(user.getPassword()))
            return null;

        return user;
    }
}
