package org.ucomplex.ucomplex.Modules.Login.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Modules.Login.LoginService;

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
public class LoginModelModule {

    private String authString;

    public LoginModelModule(String auth) {
        this.authString = auth;
    }

    @Provides
    LoginService getLoginService(){
        return ServiceGenerator.createService(LoginService.class, authString);
    }

}
