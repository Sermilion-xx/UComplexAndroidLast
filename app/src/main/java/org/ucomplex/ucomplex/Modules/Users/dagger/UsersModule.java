package org.ucomplex.ucomplex.Modules.Users.dagger;

import org.ucomplex.ucomplex.Common.interfaces.FragmentScope;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPPresenter;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.Modules.Users.UsersModel;
import org.ucomplex.ucomplex.Modules.Users.UsersPresenter;
import org.ucomplex.ucomplex.Modules.Users.model.UsersParams;

import java.util.List;

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
public class UsersModule {

    @Provides
    UsersPresenter providePresenter() {
        return new UsersPresenter();
    }

}
