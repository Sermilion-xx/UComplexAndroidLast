package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Modules.Portfolio.PortfolioService;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.DownloadFileService;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectTeachersMaterialsService;

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
public class SubjectMaterialsModelModule {

    @Provides
    SubjectTeachersMaterialsService provideService(){
        return ServiceGenerator.createService(SubjectTeachersMaterialsService.class, UCApplication.getInstance().getAuthString());
    }

    @Provides
    PortfolioService providePortfolioService(){
        return ServiceGenerator.createService(PortfolioService.class, UCApplication.getInstance().getAuthString());
    }

    @Provides
    DownloadFileService provideDownloadService(){
        return ServiceGenerator.createService(DownloadFileService.class, UCApplication.getInstance().getAuthString());
    }

}
