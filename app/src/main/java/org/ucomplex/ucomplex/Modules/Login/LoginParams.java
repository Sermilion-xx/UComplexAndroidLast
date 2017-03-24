package org.ucomplex.ucomplex.Modules.Login;

import android.content.Context;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class LoginParams {

    private String login;
    private int roleId;
    private String password;
    private Context context;

    public LoginParams(String login, String password, Context context) {
        this.login = login;
        this.password = password;
        this.context = context;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return roleId;
    }

    public void setRole(int role) {
        this.roleId = role;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
