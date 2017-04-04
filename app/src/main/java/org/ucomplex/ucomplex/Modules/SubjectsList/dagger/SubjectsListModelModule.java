package org.ucomplex.ucomplex.Modules.SubjectsList.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Modules.SubjectsList.SubjectsListService;

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
public class SubjectsListModelModule {

    @Provides
    SubjectsListService provideService() {
        return ServiceGenerator.createService(SubjectsListService.class, UCApplication.getInstance().getAuthString());
    }

}
