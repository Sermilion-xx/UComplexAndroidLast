package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherService;

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
public class RoleInfoTeacherModelModule {

    @Provides
    RoleInfoTeacherService getService() {
        return ServiceGenerator.createService(RoleInfoTeacherService.class, UCApplication.getInstance().getAuthString());
    }

}
