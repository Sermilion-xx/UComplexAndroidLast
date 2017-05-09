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
    private final String position_name;

    protected RoleStudent(Parcel in) {
        rolePrimary = in.readParcelable(RoleBase.class.getClassLoader());
        group = in.readInt();
        major = in.readInt();
        study = in.readInt();
        year = in.readInt();
        payment = in.readInt();
        contract_year = in.readInt();
        position_name = in.readString();
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

    public String getPosition_name() {
        return position_name;
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
        dest.writeString(position_name);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getPerson() {
        return 0;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getRole() {
        return 0;
    }

    @Override
    public int getPosition() {
        return 0;
    }
}
