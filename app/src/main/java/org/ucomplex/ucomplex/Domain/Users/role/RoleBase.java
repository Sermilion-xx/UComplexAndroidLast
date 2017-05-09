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
public final class RoleBase implements Role, Parcelable{

    private final int id;
    private final int person;
    private final int type;
    private final String name;
    private final int role;
    private final int position;

    public RoleBase() {
        this.id = 0;
        this.person = 0;
        this.type = 0;
        this.name = "";
        this.role = 0;
        this.position = 0;
    }

    protected RoleBase(Parcel in) {
        id = in.readInt();
        person = in.readInt();
        type = in.readInt();
        name = in.readString();
        role = in.readInt();
        position = in.readInt();
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
    }
}
