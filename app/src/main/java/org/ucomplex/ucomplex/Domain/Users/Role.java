package org.ucomplex.ucomplex.Domain.Users;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 07/11/2016.
 * Project: uComplex_v_2
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class Role implements Parcelable {

    private final int id;
    private final int person;
    private final int type;
    private final String name;

    private final int role;
    private final int group;
    private final int position;
    private final int major;
    private final int study;
    private final int year;
    private final int payment;
    private final int contract_year;
    private final String position_name;
    private final BlackList black;
    private final FriendList friends;

    private Role(Parcel in) {
        id = in.readInt();
        person = in.readInt();
        type = in.readInt();
        name = in.readString();
        role = in.readInt();
        group = in.readInt();
        position = in.readInt();
        major = in.readInt();
        study = in.readInt();
        year = in.readInt();
        payment = in.readInt();
        contract_year = in.readInt();
        position_name = in.readString();
        black = in.readParcelable(BlackList.class.getClassLoader());
        friends = in.readParcelable(FriendList.class.getClassLoader());
    }

    public static final Creator<Role> CREATOR = new Creator<Role>() {
        @Override
        public Role createFromParcel(Parcel in) {
            return new Role(in);
        }

        @Override
        public Role[] newArray(int size) {
            return new Role[size];
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

    public int getGroup() {
        return group;
    }

    public int getPosition() {
        return position;
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

    public BlackList getBlack() {
        return black;
    }

    public FriendList getFriends() {
        return friends;
    }

    @Override
    public final int describeContents() {
        return 0;
    }

    @Override
    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(person);
        dest.writeInt(type);
        dest.writeString(name);
        dest.writeInt(role);
        dest.writeInt(group);
        dest.writeInt(position);
        dest.writeInt(major);
        dest.writeInt(study);
        dest.writeInt(year);
        dest.writeInt(payment);
        dest.writeInt(contract_year);
        dest.writeString(position_name);
        dest.writeParcelable(black, flags);
        dest.writeParcelable(friends, flags);
    }
}
