package org.ucomplex.ucomplex.Domain.Users.role;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 07/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleStudent implements Role, Parcelable{

    private final RoleBase rolePrimary;
    private final int group;
    private final int major;
    private final int study;
    private final int year;
    private final int payment;
    private final int contract_year;

    private RoleStudent(Parcel in) {
        rolePrimary = in.readParcelable(RoleBase.class.getClassLoader());
        group = in.readInt();
        major = in.readInt();
        study = in.readInt();
        year = in.readInt();
        payment = in.readInt();
        contract_year = in.readInt();
    }

    public RoleStudent(RoleStudentBuilder builder) {
        rolePrimary = new RoleBase(builder.roleBaseBuilder);
        this.group = builder.group;
        this.major = builder.major;
        this.study = builder.study;
        this.year = builder.year;
        this.payment = builder.payment;
        this.contract_year = builder.contract_year;
    }

    public RoleStudent(RoleStudentBuilder builder, RoleBase roleBase) {
        rolePrimary = roleBase;
        this.group = builder.group;
        this.major = builder.major;
        this.study = builder.study;
        this.year = builder.year;
        this.payment = builder.payment;
        this.contract_year = builder.contract_year;
    }

    public static final Creator<RoleStudent> CREATOR = new Creator<RoleStudent>() {
        @Override
        public RoleStudent createFromParcel(Parcel in) {
            return new RoleStudent(in);
        }

        @Override
        public RoleStudent[] newArray(int size) {
            return new RoleStudent[size];
        }
    };

    public RoleBase getRolePrimary() {
        return rolePrimary;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(rolePrimary, flags);
        dest.writeInt(group);
        dest.writeInt(major);
        dest.writeInt(study);
        dest.writeInt(year);
        dest.writeInt(payment);
        dest.writeInt(contract_year);
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
    public String getCode() {
        return null;
    }

    @Override
    public int getPosition() {
        return rolePrimary.getPosition();
    }

    @Override
    public String getPosition_name() {
        return rolePrimary.getPosition_name();
    }


    public static class RoleStudentBuilder {

        private RoleBase.RoleBaseBuilder roleBaseBuilder;
        private RoleBase roleBase;
        private int group;
        private int major;
        private int study;
        private int year;
        private int payment;
        private int contract_year;

        public RoleStudentBuilder(RoleBase roleBase) {
            this.roleBase = roleBase;
        }

        public RoleStudentBuilder(RoleBase.RoleBaseBuilder roleBaseBuilder) {
            this.roleBaseBuilder = roleBaseBuilder;
        }

        public RoleStudent build() {
            return new RoleStudent(this);
        }

        public RoleStudentBuilder id(int id) {
            roleBaseBuilder.id(id);
            return this;
        }

        public RoleStudentBuilder person(int person) {
            roleBaseBuilder.person(person);
            return this;
        }

        public RoleStudentBuilder type(int type) {
            roleBaseBuilder.type(type);
            return this;
        }

        public RoleStudentBuilder name(String name) {
            roleBaseBuilder.name(name);
            return this;
        }

        public RoleStudentBuilder role(int role) {
            roleBaseBuilder.role(role);
            return this;
        }

        public RoleStudentBuilder position(int position) {
            roleBaseBuilder.position(position);
            return this;
        }

        public RoleStudentBuilder position_name(String position_name) {
            roleBaseBuilder.position_name(position_name);
            return this;
        }

        public RoleStudentBuilder group(int group) {
            this.group = group;
            return this;
        }

        public RoleStudentBuilder major(int major) {
            this.major = major;
            return this;
        }

        public RoleStudentBuilder study(int study) {
            this.study = study;
            return this;
        }

        public RoleStudentBuilder year(int year) {
            this.year = year;
            return this;
        }

        public RoleStudentBuilder payment(int payment) {
            this.payment = payment;
            return this;
        }

        public RoleStudentBuilder contract_year(int contract_year) {
            this.contract_year = contract_year;
            return this;
        }
    }
}
