package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.dagger;

import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.SubjectTimelineAdapter;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.SubjectTimelinePresenter;

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
public class SubjectTimelineModule {

    @Provides
    @Singleton
    SubjectTimelinePresenter providePresenter() {
        return new SubjectTimelinePresenter();
    }

    @Provides
    @Singleton
    SubjectTimelineAdapter provideAdapter() {
        return new SubjectTimelineAdapter();
    }

}
