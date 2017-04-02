package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.SubjectTimelineSeverice;

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
    SubjectTimelineSeverice provideService(){
        return ServiceGenerator.createService(SubjectTimelineSeverice.class, UCApplication.getInstance().getAuthString());
    }

}
