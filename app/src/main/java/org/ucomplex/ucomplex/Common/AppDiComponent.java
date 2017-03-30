package org.ucomplex.ucomplex.Common;

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
import org.ucomplex.ucomplex.Modules.RoleSelect.RoleSelectActivity;
import org.ucomplex.ucomplex.Modules.RoleSelect.RoleSelectPresenter;
import org.ucomplex.ucomplex.Modules.RoleSelect.dagger.RoleSelectActivityModule;
import org.ucomplex.ucomplex.Modules.RoleSelect.dagger.RoleSelectPresenterModule;
import org.ucomplex.ucomplex.Modules.Subject.SubjectActivity;
import org.ucomplex.ucomplex.Modules.Subject.SubjectModel;
import org.ucomplex.ucomplex.Modules.Subject.SubjectPresenter;
import org.ucomplex.ucomplex.Modules.Subject.dagger.SubjectActivityModule;
import org.ucomplex.ucomplex.Modules.Subject.dagger.SubjectModelModule;
import org.ucomplex.ucomplex.Modules.Subject.dagger.SubjectPresenterModule;

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
                LoginPresenterModule.class,
                RoleSelectPresenterModule.class,
                RoleSelectActivityModule.class,
                SubjectModelModule.class,
                SubjectPresenterModule.class,
                SubjectActivityModule.class})

public interface AppDiComponent {

    void inject(EventsPresenter presenter);

    void inject(EventsModel model);

    void inject(EventsActivity activity);

    void inject(LoginPresenter presenter);

    void inject(LoginModel model);

    void inject(LoginActivity activity);

    void inject(RoleSelectActivity activity);

    void inject(RoleSelectPresenter presenter);

    void inject(SubjectActivity activity);

    void inject(SubjectModel model);

    void inject(SubjectPresenter presenter);


}

