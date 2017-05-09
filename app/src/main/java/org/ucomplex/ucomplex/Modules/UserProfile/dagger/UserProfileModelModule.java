package org.ucomplex.ucomplex.Modules.UserProfile.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfileService;

import dagger.Module;
import dagger.Provides;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 03/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
@Module
public class UserProfileModelModule {

    @Provides
    UserProfileService getEventsService(){
        return ServiceGenerator.createService(UserProfileService.class, UCApplication.getInstance().getAuthString());
    }

}
