package org.ucomplex.ucomplex.Modules.Settings.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/07/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class MyInfo {

    private final int id;
    private final int payment;
    private final int group;
    private final String name;
    private final String time;
    private final String login;
    private final String pass;
    private final int closed;
    private final String group_name;
    private final String year;
    private final int study;
    private final String phone;

    public MyInfo() {
        this.id = -1;
        this.payment = -1;
        this.group = -1;
        this.name = null;
        this.time = null;
        this.login = null;
        this.pass = null;
        this.closed = -1;
        this.group_name = null;
        this.year = null;
        this.study = -1;
        this.phone = null;
    }

    public int getId() {
        return id;
    }

    public int getPayment() {
        return payment;
    }

    public int getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public int getClosed() {
        return closed;
    }

    public String getGroup_name() {
        return group_name;
    }

    public String getYear() {
        return year;
    }

    public int getStudy() {
        return study;
    }

    public String getPhone() {
        return phone;
    }
}
