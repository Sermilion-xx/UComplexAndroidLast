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

public class RoleInfoTeacherProfileItem {

    private final String key;
    private final String value;
    private final double valueInt;
    private final String online;

    public RoleInfoTeacherProfileItem() {
        this.key = "";
        this.value = "";
        this.valueInt = 0;
        this.online = "";
    }

    public RoleInfoTeacherProfileItem(String key, double value, String online) {
        this.key = key;
        this.valueInt = value;
        this.value = "";
        this.online = online;
    }

    public RoleInfoTeacherProfileItem(String key, String value) {
        this.key = key;
        this.value = value;
        this.valueInt = 0.0;
        this.online = "";
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

    public String getOnline() {
        return online;
    }
}
