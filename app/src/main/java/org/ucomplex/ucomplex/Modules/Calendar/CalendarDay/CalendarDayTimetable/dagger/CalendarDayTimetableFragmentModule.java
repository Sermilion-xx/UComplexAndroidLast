package org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable.dagger;

import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.CalendarBeltAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayBelt.CalendarDayBeltPresenter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable.CalendarDayTimetableAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarDay.CalendarDayTimetable.CalendarDayTimetablePresenter;

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
public class CalendarDayTimetableFragmentModule {

    @Provides
    @Singleton
    CalendarDayTimetablePresenter providePresenter() {
        return new CalendarDayTimetablePresenter();
    }

    @Provides
    CalendarDayTimetableAdapter provideAdapter() {
        return new CalendarDayTimetableAdapter();
    }

}
