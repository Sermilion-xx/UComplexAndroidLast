package org.ucomplex.ucomplex.Modules.Login.model;

import com.google.gson.annotations.SerializedName;


import org.ucomplex.ucomplex.Domain.Users.Role;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 16/03/2017.
 * Project: UcomplexIslam
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class LoginUser {

    List<Role> roles;
    Session session;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
