package org.ucomplex.ucomplex.Domain.Users;

import org.ucomplex.ucomplex.Domain.Users.role.Role;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 07/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class Student implements UserInterface{

    private User user;

    @Override
    public int getType() {
        return user.getType();
    }

    @Override
    public String getLogin() {
        return user.getLogin();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public List<Role> getRoles() {
        return user.getRoles();
    }

    @Override
    public int getPhoto() {
        return user.getPhoto();
    }

    @Override
    public String getCode() {
        return user.getCode();
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public int getId() {
        return user.getId();
    }

    @Override
    public int getPerson() {
        return user.getPerson();
    }
}
