package org.ucomplex.ucomplex;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model.SubjectRaw;

import java.lang.reflect.Type;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 20/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UnitTestUtils {

    public static  <T> T  rawFromJson(String fileName) {
        String json = FacadeCommon.readFile(fileName);
        Gson gson = new Gson();
        Type typeToken = new TypeToken<T>(){}.getType();
        return gson.fromJson(json, typeToken);
    }
}
