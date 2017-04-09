package org.yuval.objects;

import java.util.ArrayList;

/**
 * Created by Yuval on 16-Mar-17.
 * user object
 */
public class User {
    private String userName, password;
    private ArrayList<UserShows> userShows = new ArrayList<>();
    private boolean isAdmin;

    public User() {
    }

    public User(String user, String password, boolean isAdmin) {
        super();
        this.userName = user;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<UserShows> getUserShows() {
        return userShows;
    }

    public void setUserShows(ArrayList<UserShows> userShows) {
        this.userShows = userShows;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
