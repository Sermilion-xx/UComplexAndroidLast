package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.dagger;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Portfolio.FileService;
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
    SubjectTeachersMaterialsService provideTeachersFilesService(){
        return ServiceGenerator.createService(SubjectTeachersMaterialsService.class, UCApplication.getInstance().getAuthString());
    }

    @Provides
    FileService provideFileService(){
        return ServiceGenerator.createService(FileService.class, UCApplication.getInstance().getAuthString());
    }

}
