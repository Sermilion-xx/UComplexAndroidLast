package org.ucomplex.ucomplex.Modules.Login.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.ucomplex.ucomplex.Domain.Users.Role;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 16/03/2017.
 * Project: UcomplexIslam
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class Session implements Parcelable {
    //тип клиента - с мобтльного или нет? (0 - десктоп, 1 - андроид, 2 - ios)
    private final int mobile;
    private final String code;
    private final int photo;
    private final String name;
    private final String login;
    private final String pass;
    private final String phone;
    private final String email;
    private final String session;
    private final int person;
    private final int client;
    private final List<Role> roles;


    protected Session(Parcel in) {
        mobile = in.readInt();
        code = in.readString();
        photo = in.readInt();
        name = in.readString();
        login = in.readString();
        pass = in.readString();
        phone = in.readString();
        email = in.readString();
        session = in.readString();
        person = in.readInt();
        client = in.readInt();
        roles = in.createTypedArrayList(Role.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mobile);
        dest.writeString(code);
        dest.writeInt(photo);
        dest.writeString(name);
        dest.writeString(login);
        dest.writeString(pass);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(session);
        dest.writeInt(person);
        dest.writeInt(client);
        dest.writeTypedList(roles);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Session> CREATOR = new Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    public int getMobile() {
        return mobile;
    }

    public String getCode() {
        return code;
    }

    public int getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getSession() {
        return session;
    }

    public int getPerson() {
        return person;
    }

    public int getClient() {
        return client;
    }

    public List<Role> getRoles() {
        return roles;
    }

}
