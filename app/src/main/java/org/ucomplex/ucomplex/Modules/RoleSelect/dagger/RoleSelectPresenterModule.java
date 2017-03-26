package org.ucomplex.ucomplex.Modules.RoleSelect.dagger;

import org.ucomplex.ucomplex.Modules.Login.LoginModel;
import org.ucomplex.ucomplex.Modules.RoleSelect.RoleSelectModel;

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
public class RoleSelectPresenterModule {

    @Provides
    @Singleton
    public RoleSelectModel provideModel() {
        return new RoleSelectModel();
    }

}
