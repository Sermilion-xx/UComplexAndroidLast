package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.dagger;

import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherPresenter;

import javax.inject.Singleton;

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
public class RoleInfoTeacherActivityModule {

    @Provides
    @Singleton
    RoleInfoTeacherPresenter providePresenter() {
        return new RoleInfoTeacherPresenter();
    }
}
