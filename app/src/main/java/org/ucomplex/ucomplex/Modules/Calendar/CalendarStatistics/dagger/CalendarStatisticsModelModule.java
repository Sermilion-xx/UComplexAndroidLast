package org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPageService;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarStatistics.CalendarStatisticsService;

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
public class CalendarStatisticsModelModule {

    @Provides
    CalendarStatisticsService getEventsService() {
        return ServiceGenerator.createService(CalendarStatisticsService.class, UCApplication.getInstance().getAuthString());
    }

}
