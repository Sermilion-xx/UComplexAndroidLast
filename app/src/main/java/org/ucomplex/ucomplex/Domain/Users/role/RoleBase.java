package org.ucomplex.ucomplex.Domain.Users.role;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 07/11/2016.
 * Project: uComplex_v_2
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

/**
 * This class is used to represent generic roles on login stage
 */
public final class RoleBase implements Role, Parcelable {

    private final int id;
    private final int person;
    private final int type;
    private final String name;
    private final int role;
    private final int position;
    private final String position_name;
    private final String code;

    public RoleBase(RoleBaseBuilder builder) {
        this.id = builder.id;
        this.person = builder.person;
        this.type = builder.type;
        this.name = builder.name;
        this.role = builder.role;
        this.position = builder.position;
        this.position_name = builder.position_name;
        this.code = builder.code;
    }

    public RoleBase() {
        this.id = 0;
        this.person = 0;
        this.type = 0;
        this.name = "";
        this.role = 0;
        this.position = 0;
        this.position_name = "";
        this.code = "";
    }

    protected RoleBase(Parcel in) {
        id = in.readInt();
        person = in.readInt();
        type = in.readInt();
        name = in.readString();
        role = in.readInt();
        position = in.readInt();
        position_name = in.readString();
        code = in.readString();
    }

    public static final Creator<RoleBase> CREATOR = new Creator<RoleBase>() {
        @Override
        public RoleBase createFromParcel(Parcel in) {
            return new RoleBase(in);
        }

        @Override
        public RoleBase[] newArray(int size) {
            return new RoleBase[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getPerson() {
        return person;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }

    @Override
    public String getCode() {
        return null;
    }

    public String getPosition_name() {
        return position_name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(person);
        dest.writeInt(type);
        dest.writeString(name);
        dest.writeInt(role);
        dest.writeInt(position);
        dest.writeString(position_name);
        dest.writeString(code);
    }

    public static class RoleBaseBuilder {

        protected int id;
        protected int person;
        protected int type;
        protected String name;
        protected int role;
        protected int position;
        protected String position_name;
        protected String code;

        public RoleBase build() {
            return new RoleBase(this);
        }

        public RoleBaseBuilder id(int id) {
            this.id = id;
            return this;
        }

        public RoleBaseBuilder person(int person) {
            this.person = person;
            return this;
        }

        public RoleBaseBuilder type(int type) {
            this.type = type;
            return this;
        }

        public RoleBaseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RoleBaseBuilder role(int role) {
            this.role = role;
            return this;
        }

        public RoleBaseBuilder position(int position) {
            this.position = position;
            return this;
        }

        public RoleBaseBuilder position_name(String position_name) {
            this.position_name = position_name;
            return this;
        }

        public RoleBaseBuilder code(String code) {
            this.code = code;
            return this;
        }
    }
}
