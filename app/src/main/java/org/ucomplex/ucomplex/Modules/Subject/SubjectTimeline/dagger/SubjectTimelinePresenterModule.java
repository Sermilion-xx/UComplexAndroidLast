package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.dagger;

import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.SubjectModel;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.SubjectTimelineModel;

import javax.inject.Singleton;

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
public class SubjectTimelinePresenterModule {

    @Provides
    @Singleton
    SubjectTimelineModel provideModel() {
        return new SubjectTimelineModel();
    }

}
