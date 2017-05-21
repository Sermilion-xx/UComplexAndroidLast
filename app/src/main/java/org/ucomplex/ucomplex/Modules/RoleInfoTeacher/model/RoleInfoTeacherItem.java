package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 21/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleInfoTeacherItem {

    private final String key;
    private final String value;
    private final double valueInt;

    public RoleInfoTeacherItem() {
        this.key = "";
        this.value = "";
        this.valueInt = 0;
    }

    public RoleInfoTeacherItem(String key, double value) {
        this.key = key;
        this.valueInt = value;
        this.value = "";
    }

    public RoleInfoTeacherItem(String key, String value) {
        this.key = key;
        this.value = value;
        this.valueInt = 0.0;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public double getValueInt() {
        return valueInt;
    }
}
