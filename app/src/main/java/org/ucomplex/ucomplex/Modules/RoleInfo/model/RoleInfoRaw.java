package org.ucomplex.ucomplex.Modules.RoleInfo.model;

import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model.Progress;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class RoleInfoRaw {

    private final int id;
    private final String name;
    private final int type;
    private final int closed;
    private final String alias;
    private final int agent;
    private final long online;
    private final int study;
    private final int payment;
    private final int group;
    private final String group_name;
    private final int year;
    private final String major_name;
    private final int study_level;
    private final String faculty_name;
    private final Rating rating;
    private final Progress progress;

    public RoleInfoRaw() {
        this.id = 0;
        this.name = "";
        this.type = 0;
        this.closed = 0;
        this.alias = "";
        this.agent = 0;
        this.online = 0;
        this.study = 0;
        this.payment = 0;
        this.group = 0;
        this.group_name = "";
        this.year = 0;
        this.major_name = "";
        this.study_level = 0;
        this.faculty_name = "";
        this.rating = new Rating();
        this.progress = new Progress();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getClosed() {
        return closed;
    }

    public String getAlias() {
        return alias;
    }

    public int getAgent() {
        return agent;
    }

    public long getOnline() {
        return online;
    }

    public int getStudy() {
        return study;
    }

    public int getPayment() {
        return payment;
    }

    public int getGroup() {
        return group;
    }

    public String getGroup_name() {
        return group_name;
    }

    public int getYear() {
        return year;
    }

    public String getMajor_name() {
        return major_name;
    }

    public int getStudy_level() {
        return study_level;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public Rating getRating() {
        return rating;
    }

    public Progress getProgress() {
        return progress;
    }
}
