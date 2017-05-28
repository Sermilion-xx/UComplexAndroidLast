package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class RoleInfoTeacherRawSingleton {

    private RoleInfoTeacherRaw rawData;

    public static RoleInfoTeacherRawSingleton getInstance() {
        return RoleInfoTeacherRawSingletonHelper.INSTANCE;
    }

    private static class RoleInfoTeacherRawSingletonHelper {
        private static final RoleInfoTeacherRawSingleton INSTANCE = new RoleInfoTeacherRawSingleton();
    }

    public void setRawData(RoleInfoTeacherRaw rawData) {
        this.rawData = rawData;
    }

    public RoleInfoTeacherRaw getRawData() {
        return rawData;
    }
}
