package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.dagger;

import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.CalendarBeltAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.CalendarDayBeltAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.CalendarDayBeltPresenter;

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
public class CalendarDayBeltFragmentModule {

    @Provides
    @Singleton
    CalendarDayBeltPresenter providePresenter() {
        return new CalendarDayBeltPresenter();
    }

    @Provides
    CalendarBeltAdapter provideAdapter() {
        return new CalendarBeltAdapter();
    }


}
