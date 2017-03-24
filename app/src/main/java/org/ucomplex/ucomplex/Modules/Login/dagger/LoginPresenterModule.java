package org.ucomplex.ucomplex.Modules.Login.dagger;

import org.ucomplex.ucomplex.Modules.Events.EventsModel;
import org.ucomplex.ucomplex.Modules.Login.LoginModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
@Module
public class LoginPresenterModule {

    @Provides
    @Singleton
    public LoginModel provideModel() {
        return new LoginModel();
    }

}
