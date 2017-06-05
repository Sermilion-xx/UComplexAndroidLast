package org.ucomplex.ucomplex.Modules.Messenger.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class CompanionInfo {

    private final int id;
    private final String name;
    private final String code;
    private final int photo;

    public CompanionInfo() {
        this.id = 0;
        this.name = "";
        this.code = "";
        this.photo = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getPhoto() {
        return photo;
    }
}