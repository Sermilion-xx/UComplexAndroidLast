package org.ucomplex.ucomplex.Modules.Portfolio;

import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.MaterialsRaw;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 08/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface PortfolioService {
    @GET("/student/my_files?mobile=1")
    Observable<MaterialsRaw> getPortfolio();
}
