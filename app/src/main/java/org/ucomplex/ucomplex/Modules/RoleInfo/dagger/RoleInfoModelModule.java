package org.ucomplex.ucomplex.Modules.RoleInfo.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.RoleInfo.RoleInfoService;

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
public class RoleInfoModelModule {

    @Provides
    RoleInfoService getService() {
        return ServiceGenerator.createService(RoleInfoService.class, UCApplication.getInstance().getAuthString());
    }

}
