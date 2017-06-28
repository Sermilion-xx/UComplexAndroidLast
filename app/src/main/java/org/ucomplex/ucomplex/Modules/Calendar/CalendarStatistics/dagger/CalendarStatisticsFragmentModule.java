package org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.dagger;

import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.CalendarStatisticsAdapter;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.CalendarStatisticsPresenter;

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
public class CalendarStatisticsFragmentModule {

    @Provides
    @Singleton
    CalendarStatisticsPresenter providePresenter() {
        return new CalendarStatisticsPresenter();
    }

    @Provides
    @Singleton
    CalendarStatisticsAdapter provideAdapter() {
        return new CalendarStatisticsAdapter();
    }

}
