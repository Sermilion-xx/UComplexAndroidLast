package org.ucomplex.ucomplex.Modules.Login.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.ucomplex.ucomplex.Domain.Users.Student;
import org.ucomplex.ucomplex.Domain.Users.Teacher;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Domain.Users.role.RoleBase;

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

public final class LoginUser implements Parcelable {

    private final List<RoleBase> roles;
    private final Session session;

    protected LoginUser(Parcel in) {
        session = in.readParcelable(Session.class.getClassLoader());
        roles = in.createTypedArrayList(RoleBase.CREATOR);
    }

    public List<RoleBase> getRoles() {
        return roles;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(session, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginUser> CREATOR = new Creator<LoginUser>() {
        @Override
        public LoginUser createFromParcel(Parcel in) {
            return new LoginUser(in);
        }

        @Override
        public LoginUser[] newArray(int size) {
            return new LoginUser[size];
        }
    };

    public final UserInterface extractUser(int rolePos) {
        User.UserBuilder builder = new User.UserBuilder();
        builder.roles(roles);
        builder.mobile(session.getMobile());
        builder.code(session.getCode());
        builder.photo(session.getPhoto());
        builder.name(session.getName());
        builder.login(session.getLogin());
        builder.password(session.getPass());
        builder.phone(session.getPhone());
        builder.email(session.getEmail());
        builder.session(session.getSession());
        builder.person(session.getPerson());
        builder.client(session.getClient());
        builder.id(roles.get(rolePos).getId());
        builder.type(roles.get(rolePos).getType());

        UserInterface result;
        User userInterface = new User(builder);
        if (userInterface.getType() == User.USER_TYPE_STUDENT) {
            result = new Student(userInterface);
        } else if (userInterface.getType() == User.USER_TYPE_TEACHER) {
            result = new Teacher(userInterface);
        } else {
            result = userInterface;
        }
        return result;
    }
}
