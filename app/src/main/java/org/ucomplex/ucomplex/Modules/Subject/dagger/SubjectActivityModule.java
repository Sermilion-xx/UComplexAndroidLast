package org.ucomplex.ucomplex.Modules.Subject.dagger;

import org.ucomplex.ucomplex.Modules.Subject.SubjectPresenter;

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
public class SubjectActivityModule {

    @Provides
    @Singleton
    SubjectPresenter providePresenter() {
        return new SubjectPresenter();
    }

}
