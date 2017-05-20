package org.ucomplex.ucomplex.Modules.RoleInfo.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 20/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class Rating {
    private final int general;
    private final int faculty;

    public Rating() {
        this.general = 0;
        this.faculty = 0;
    }

    public int getGeneral() {
        return general;
    }

    public int getFaculty() {
        return faculty;
    }
}
