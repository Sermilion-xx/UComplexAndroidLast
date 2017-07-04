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

public final class Info {

    private int person;
    private int id;
    private String login;
    private String pass;
    private String name;
    private int academic_degree;
    private int academic_rank;
    private String academic_awards;
    private String upqualification;
    private int sex;
    private String code;
    private String alias;
    private String photo;
    private String email;
    private String phone;
    private int type;
    private int status;
    private int agent;
    private int closed;
    private int searchable;
    private long online;
    private String time;
    private String bio;
    private String data;
    private String ip;
    private int client;

    public Info() {
        this.person = -1;
        this.id = -1;
        this.login = null;
        this.pass = null;
        this.name = null;
        this.academic_degree = -1;
        this.academic_rank = -1;
        this.academic_awards = null;
        this.upqualification = null;
        this.sex = -1;
        this.code = null;
        this.alias = null;
        this.photo = null;
        this.email = null;
        this.phone = null;
        this.type = -1;
        this.status = -1;
        this.agent = -1;
        this.closed = -1;
        this.searchable = -1;
        this.online = -1;
        this.time = null;
        this.bio = null;
        this.data = null;
        this.ip = null;
        this.client = -1;
    }

    public int getPerson() {
        return person;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public int getAcademic_degree() {
        return academic_degree;
    }

    public int getAcademic_rank() {
        return academic_rank;
    }

    public String getAcademic_awards() {
        return academic_awards;
    }

    public String getUpqualification() {
        return upqualification;
    }

    public int getSex() {
        return sex;
    }

    public String getCode() {
        return code;
    }

    public String getAlias() {
        return alias;
    }

    public String getPhoto() {
        return photo;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public int getAgent() {
        return agent;
    }

    public int getClosed() {
        return closed;
    }

    public int getSearchable() {
        return searchable;
    }

    public long getOnline() {
        return online;
    }

    public String getTime() {
        return time;
    }

    public String getBio() {
        return bio;
    }

    public String getData() {
        return data;
    }

    public String getIp() {
        return ip;
    }

    public int getClient() {
        return client;
    }
}
