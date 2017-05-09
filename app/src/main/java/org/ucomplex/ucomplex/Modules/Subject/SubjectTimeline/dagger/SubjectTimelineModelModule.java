package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.SubjectTimelineService;

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
public class SubjectTimelineModelModule {

    @Provides
    SubjectTimelineService provideService(){
        return ServiceGenerator.createService(SubjectTimelineService.class, UCApplication.getInstance().getAuthString());
    }

}
