package org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.dagger;

import org.ucomplex.ucomplex.Modules.Calendar.CalendarBelt.CalendarBeltModel;
import org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.CalendarPageModel;

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
public class CalendarBeltPresenterModule {

    @Provides
    @Singleton
    CalendarBeltModel provideModel() {
        return new CalendarBeltModel();
    }

}
