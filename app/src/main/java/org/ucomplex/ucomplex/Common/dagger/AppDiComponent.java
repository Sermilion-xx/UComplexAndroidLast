package org.ucomplex.ucomplex.Common.dagger;

import org.ucomplex.ucomplex.Modules.Events.EventsActivity;
import org.ucomplex.ucomplex.Modules.Events.EventsModel;
import org.ucomplex.ucomplex.Modules.Events.EventsPresenter;
import org.ucomplex.ucomplex.Modules.Events.dagger.EventsActivityModule;
import org.ucomplex.ucomplex.Modules.Events.dagger.EventsModelModule;
import org.ucomplex.ucomplex.Modules.Events.dagger.EventsPresenterModule;
import org.ucomplex.ucomplex.Modules.Login.LoginActivity;
import org.ucomplex.ucomplex.Modules.Login.LoginModel;
import org.ucomplex.ucomplex.Modules.Login.LoginPresenter;
import org.ucomplex.ucomplex.Modules.Login.dagger.LoginActivityModule;
import org.ucomplex.ucomplex.Modules.Login.dagger.LoginModelModule;
import org.ucomplex.ucomplex.Modules.Login.dagger.LoginPresenterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 10/02/2017.
 * Project: Listening
 * ---------------------------------------------------
 * <a href="http://www.skyeng.ru">www.skyeng.ru</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
@Singleton
@Component(modules =
        {EventsActivityModule.class,
                EventsModelModule.class,
                EventsPresenterModule.class,
                LoginActivityModule.class,
                LoginModelModule.class,
                LoginPresenterModule.class})

public interface AppDiComponent {

    void inject(EventsPresenter presenter);

    void inject(EventsModel model);

    void inject(EventsActivity activity);

    void inject(LoginPresenter presenter);

    void inject(LoginModel model);

    void inject(LoginActivity activity);

}

