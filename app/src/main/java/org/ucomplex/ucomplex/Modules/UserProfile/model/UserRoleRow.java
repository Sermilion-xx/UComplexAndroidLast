package org.ucomplex.ucomplex.Modules.UserProfile.model;

import org.ucomplex.ucomplex.Domain.Users.role.Role;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 15/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class UserRoleRow implements Role {

    private final int id;
    private final int person;
    private final int type;
    private final String name;
    private final int role;
    private final int position;
    private final String position_name;

    private final int group;
    private final int major;
    private final int study;
    private final int year;
    private final int payment;
    private final int contract_year;

    private final float rate;
    private final int employment_type;
    private final int public_role;
    private final String login;
    private final int photo;
    private final String code;
    private final String email;
    private final String alias;
    private final int section;
    private final String section_name;
    private final int lead;
    private final int _public;

    public UserRoleRow() {
        this.id = 0;
        this.person = 0;
        this.type = 0;
        this.name = "";
        this.role = 0;
        this.position = 0;
        this.position_name = "";

        this.group = 0;
        this.major = 0;
        this.study = 0;
        this.year = 0;
        this.payment = 0;
        this.contract_year = 0;

        this.rate = 0.0f;
        this.employment_type = 0;
        this.public_role = 0;
        this.login = "";
        this.photo = 0;
        this.code = "";
        this.email = "";
        this.alias = "";
        this.section = 0;
        this.section_name = "";
        this.lead = 0;
        this._public = 0;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getPerson() {
        return person;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRole() {
        return role;
    }

    @Override
    public int getPosition() {
        return position;
    }

    public String getPosition_name() {
        return position_name;
    }

    public int getGroup() {
        return group;
    }

    public int getMajor() {
        return major;
    }

    public int getStudy() {
        return study;
    }

    public int getYear() {
        return year;
    }

    public int getPayment() {
        return payment;
    }

    public int getContract_year() {
        return contract_year;
    }

    public float getRate() {
        return rate;
    }

    public int getEmployment_type() {
        return employment_type;
    }

    public int getPublic_role() {
        return public_role;
    }

    public String getLogin() {
        return login;
    }

    public int getPhoto() {
        return photo;
    }

    public String getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    public String getAlias() {
        return alias;
    }

    public int getSection() {
        return section;
    }

    public String getSection_name() {
        return section_name;
    }

    public int getLead() {
        return lead;
    }

    public int get_public() {
        return _public;
    }
}
