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
    private final int section;
    private final String section_name;
    private final int lead;
    private final int _public;

    private RoleTeacher() {
        this.rolePrimary = new RoleTeacher();
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

    public RoleTeacher(RoleTeacherBuilder builder) {
        this.rolePrimary = new RoleBase(builder.roleBaseBuilder);
        this.rate = builder.rate;
        this.employment_type = builder.employment_type;
        this.public_role = builder.public_role;
        this.login = builder.login;
        this.photo = builder.photo;
        this.code = builder.code;
        this.email = builder.email;
        this.alias = builder.alias;
        this.section = builder.section;
        this.section_name = builder.section_name;
        this.lead = builder.lead;
        this._public = builder._public;
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

    @Override
    public String getPosition_name() {
        return rolePrimary.getPosition_name();
    }

    public static class RoleTeacherBuilder {
        private RoleBase.RoleBaseBuilder roleBaseBuilder;
        private float rate;
        private int employment_type;
        private int public_role;
        private String login;
        private int photo;
        private String code;
        private String email;
        private String alias;
        private int section;
        private String section_name;
        private int lead;
        private int _public;

        public RoleTeacherBuilder(RoleBase.RoleBaseBuilder roleBaseBuilder) {
            this.roleBaseBuilder = roleBaseBuilder;
        }

        public RoleTeacher build() {
            return new RoleTeacher(this);
        }

        public RoleTeacherBuilder id(int id) {
            roleBaseBuilder.id(id);
            return this;
        }

        public RoleTeacherBuilder person(int person) {
            roleBaseBuilder.person(person);
            return this;
        }

        public RoleTeacherBuilder type(int type) {
            roleBaseBuilder.type(type);
            return this;
        }

        public RoleTeacherBuilder name(String name) {
            roleBaseBuilder.name(name);
            return this;
        }

        public RoleTeacherBuilder role(int role) {
            roleBaseBuilder.role(role);
            return this;
        }

        public RoleTeacherBuilder position(int position) {
            roleBaseBuilder.position(position);
            return this;
        }

        public RoleTeacherBuilder position_name(String position_name) {
            roleBaseBuilder.position_name(position_name);
            return this;
        }

        public RoleTeacherBuilder rate(float rate) {
            this.rate = rate;
            return this;
        }

        public RoleTeacherBuilder employment_type(int employment_type) {
            this.employment_type = employment_type;
            return this;
        }

        public RoleTeacherBuilder public_role(int public_role) {
            this.public_role = public_role;
            return this;
        }

        public RoleTeacherBuilder login(String login) {
            this.login = login;
            return this;
        }

        public RoleTeacherBuilder photo(int photo) {
            this.photo = photo;
            return this;
        }

        public RoleTeacherBuilder code(String code) {
            this.code = code;
            return this;
        }

        public RoleTeacherBuilder email(String email) {
            this.email = email;
            return this;
        }

        public RoleTeacherBuilder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public RoleTeacherBuilder section(int section) {
            this.section = section;
            return this;
        }

        public RoleTeacherBuilder section_name(String section_name) {
            this.section_name = section_name;
            return this;
        }

        public RoleTeacherBuilder lead(int lead) {
            this.lead = lead;
            return this;
        }

        public RoleTeacherBuilder _public(int _public) {
            this._public = _public;
            return this;
        }
    }
}
