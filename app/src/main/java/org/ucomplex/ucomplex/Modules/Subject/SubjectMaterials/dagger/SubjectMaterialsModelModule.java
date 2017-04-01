package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.SubjectMaterialsService;

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
    SubjectMaterialsService provideService(){
        return ServiceGenerator.createService(SubjectMaterialsService.class, UCApplication.getInstance().getAuthString());
    }

}
