package org.ucomplex.ucomplex.Modules.Portfolio.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Modules.Events.EventsService;
import org.ucomplex.ucomplex.Modules.Portfolio.PortfolioService;

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
public class PortfolioModelModule {

    @Provides
    PortfolioService provideService(){
        return ServiceGenerator.createService(PortfolioService.class, UCApplication.getInstance().getAuthString());
    }

}
