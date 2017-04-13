package org.ucomplex.ucomplex.Modules.Users.dagger;

import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.SubjectModel;
import org.ucomplex.ucomplex.Modules.Users.UsersOnline.UsersOnlineModel;

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
public class UsersOnlinePresenterModule {

    @Provides
    UsersOnlineModel provideModel() {
        return new UsersOnlineModel();
    }

}
