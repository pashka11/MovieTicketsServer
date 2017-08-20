package nimrod.cinema.Managers;

import nimrod.cinema.dao.DataAccessObject;
import nimrod.cinema.objects.MovieDetails;
import nimrod.cinema.objects.Screening;
import nimrod.cinema.objects.User;

public class UserManager {

    private DataAccessObject<User> _userDao;

    public UserManager()
    {
        _userDao = new DataAccessObject<>(User.class);
    }

    public User HandleValidateUser(User user)
    {
        User userFromDb = _userDao.ReadOne(user.get_user());

        if (userFromDb == null || !userFromDb.get_password().equals(user.get_password()))
            return null;

        return user;
    }
}
