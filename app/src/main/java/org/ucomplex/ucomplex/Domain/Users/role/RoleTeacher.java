package org.ucomplex.ucomplex.Domain.Users.role;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 06/12/2016.
 * Project: uComplex_v_2
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class RoleTeacher implements Role {

    private final Role rolePrimary;
    private final float rate;
    private final int employment_type;
    private final int public_role;
    private final String login;
    private final int photo;
    private final String code;
    private final String email;
    private final String alias;
    private final String position_name;
    private final int section;
    private final String section_name;
    private final int lead;
    private final int pulbic;

    public RoleTeacher() {
        this.rolePrimary = new RoleTeacher();
        this.rate = 0.0f;
        this.employment_type = 0;
        this.public_role = 0;
        this.login = "";
        this.photo = 0;
        this.code = "";
        this.email = "";
        this.alias = "";
        this.position_name = "";
        this.section = 0;
        this.section_name = "";
        this.lead = 0;
        this.pulbic = 0;
    }

    public Role getRolePrimary() {
        return rolePrimary;
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

    public String getPosition_name() {
        return position_name;
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

    public int getPulbic() {
        return pulbic;
    }

    @Override
    public int getId() {
        return rolePrimary.getId();
    }

    @Override
    public int getPerson() {
        return rolePrimary.getPerson();
    }

    @Override
    public int getType() {
        return rolePrimary.getType();
    }

    @Override
    public String getName() {
        return rolePrimary.getName();
    }

    @Override
    public int getRole() {
        return rolePrimary.getRole();
    }

    @Override
    public int getPosition() {
        return rolePrimary.getPosition();
    }
}
