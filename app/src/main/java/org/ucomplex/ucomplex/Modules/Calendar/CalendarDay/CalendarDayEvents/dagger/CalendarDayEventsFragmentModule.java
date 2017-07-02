package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayEvents.dagger;

import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayEvents.CalendarDayEventsAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayEvents.CalendarDayEventsPresenter;

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
public class CalendarDayEventsFragmentModule {

    @Provides
    @Singleton
    CalendarDayEventsPresenter providePresenter() {
        return new CalendarDayEventsPresenter();
    }

    @Provides
    CalendarDayEventsAdapter provideAdapter() {
        return new CalendarDayEventsAdapter();
    }


}
