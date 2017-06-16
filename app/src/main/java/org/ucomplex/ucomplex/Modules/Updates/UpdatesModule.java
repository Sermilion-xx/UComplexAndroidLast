package org.ucomplex.ucomplex.Modules.Updates;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Messenger.MessengerService;

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
public class UpdatesModule {

    @Provides
    UpdatesRetrofitService getService() {
        return ServiceGenerator.createService(UpdatesRetrofitService.class, UCApplication.getInstance().getAuthString());
    }

}
