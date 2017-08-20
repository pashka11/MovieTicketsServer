package nimrod.cinema.objects;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("user")
public class User {

    @Id
    private String _user;
    private String _password;

    public User()
    {

    }

    public User(String password,String user)
    {
        this._user = user;
        this._password = password;
    }

    public String get_password()
    {
        return _password;
    }

    public String get_user()
    {
        return _user;
    }
}
