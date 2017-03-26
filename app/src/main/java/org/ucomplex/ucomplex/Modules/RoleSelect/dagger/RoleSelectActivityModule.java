package org.ucomplex.ucomplex.Modules.RoleSelect.dagger;

import org.ucomplex.ucomplex.Modules.Login.LoginPresenter;
import org.ucomplex.ucomplex.Modules.RoleSelect.RoleSelectPresenter;

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
public class RoleSelectActivityModule {

    @Provides
    @Singleton
    public RoleSelectPresenter providePresenter() {
        return new RoleSelectPresenter();
    }

}
