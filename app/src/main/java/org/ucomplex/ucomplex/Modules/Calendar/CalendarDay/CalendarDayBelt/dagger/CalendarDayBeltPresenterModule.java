package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.dagger;

import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.CalendarDayBeltModel;

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
public class CalendarDayBeltPresenterModule {

    @Provides
    @Singleton
    CalendarDayBeltModel provideModel() {
        return new CalendarDayBeltModel();
    }

}
