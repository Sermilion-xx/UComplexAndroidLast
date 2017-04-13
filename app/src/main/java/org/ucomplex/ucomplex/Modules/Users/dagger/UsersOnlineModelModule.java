package org.ucomplex.ucomplex.Modules.Users.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.SubjectService;
import org.ucomplex.ucomplex.Modules.Users.retrofit.UsersOnlineService;

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
public class UsersOnlineModelModule {

    @Provides
    UsersOnlineService provideService(){
        return ServiceGenerator.createService(UsersOnlineService.class, UCApplication.getInstance().getAuthString());
    }

}
