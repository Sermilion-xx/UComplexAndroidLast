package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectItemProfile {

    private String code;
    private String name;
    private int id;
    private String attendance;
    private String mark;

    public SubjectItemProfile() {
        attendance = "";
        mark = "";
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectItemProfile profile = (SubjectItemProfile) o;

        if (id != profile.id) return false;
        if (code != null ? !code.equals(profile.code) : profile.code != null) return false;
        if (name != null ? !name.equals(profile.name) : profile.name != null) return false;
        if (attendance != null ? !attendance.equals(profile.attendance) : profile.attendance != null)
            return false;
        return mark != null ? mark.equals(profile.mark) : profile.mark == null;

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (attendance != null ? attendance.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        return result;
    }
}
