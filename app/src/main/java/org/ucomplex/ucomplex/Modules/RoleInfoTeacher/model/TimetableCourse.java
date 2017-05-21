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

public final class TimetableCourse {

    private final int id;
    private final String name;
    private final String description;
    private final int cat;
    private final int type;
    private final int department;
    private final int client;

    public TimetableCourse() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.cat = 0;
        this.type = 0;
        this.department = 0;
        this.client = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCat() {
        return cat;
    }

    public int getType() {
        return type;
    }

    public int getDepartment() {
        return department;
    }

    public int getClient() {
        return client;
    }
}
