package org.ucomplex.ucomplex.Common.base;

import android.app.Application;

import org.ucomplex.ucomplex.Common.FacadePreferences;
import org.ucomplex.ucomplex.Common.dagger.AppDiComponent;
import org.ucomplex.ucomplex.Common.dagger.DaggerAppDiComponent;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/03/2017.
 * Project: OneAccount
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UCApplication extends Application {

    private static UCApplication INSTANCE;
    public  static UCApplication getInstance() {
        return INSTANCE;
    }
    private static String authString;
    private UserInterface loggedUser;

    private AppDiComponent appDiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        authString = FacadePreferences.getLoginDataFromPref(this);
        loggedUser = FacadePreferences.getUserDataFromPref(this);
        appDiComponent = DaggerAppDiComponent.builder().build();
    }

    public AppDiComponent getAppDiComponent() {
        return appDiComponent;
    }

    public static String getAuthString() {
        return authString;
    }

    public UserInterface getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserInterface loggedUser) {
        this.loggedUser = loggedUser;
    }
}
