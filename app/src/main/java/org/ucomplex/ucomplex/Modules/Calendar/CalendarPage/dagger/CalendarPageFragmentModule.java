package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.dagger;

import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPagePresenter;

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
public class CalendarPageFragmentModule {

    @Provides
    @Singleton
    CalendarPagePresenter providePresenter() {
        return new CalendarPagePresenter();
    }

}
