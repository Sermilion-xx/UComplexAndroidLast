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

final class LoginParams {

    private final String login;
    private final String password;
    private final Context context;

    LoginParams(String login, String password, Context context) {
        this.login = login;
        this.password = password;
        this.context = context;
    }

    public String getLogin() {
        return login;
    }

    String getPassword() {
        return password;
    }

    public Context getContext() {
        return context;
    }

}
