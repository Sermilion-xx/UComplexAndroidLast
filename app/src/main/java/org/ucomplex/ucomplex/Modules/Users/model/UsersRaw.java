package org.ucomplex.ucomplex.Modules.Users.model;

import org.ucomplex.ucomplex.Domain.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 13/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class UsersRaw {

    private final boolean online;
    private final List<User> users;
    private final List<User> friends;

    public UsersRaw() {
        this.online = false;
        this.users = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    public List<User> getFriends() {
        return new ArrayList<>(friends);
    }

    public boolean isOnline() {
        return online;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}
