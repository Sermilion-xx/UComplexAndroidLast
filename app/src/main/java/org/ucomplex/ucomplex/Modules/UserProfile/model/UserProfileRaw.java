package org.ucomplex.ucomplex.Modules.UserProfile.model;

import org.ucomplex.ucomplex.Domain.Users.BlackList;
import org.ucomplex.ucomplex.Domain.Users.FriendList;
import org.ucomplex.ucomplex.Domain.Users.Teacher;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Domain.Users.role.Role;
import org.ucomplex.ucomplex.Domain.Users.role.RoleBase;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 03/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class UserProfileRaw {

    private final int id;
    private final String name;
    private final String email;
    private final String code;
    private final int photo;
    private final String statuses;
    private final String academic_awards;
    private final int academic_rank;
    private final int academic_degree;
    private final String upqualification;
    private final String phone_work;
    private final String bio;
    private final List<UserRoleRow> roles;
    private final BlackList black;
    private final FriendList friend;

    public UserProfileRaw() {
        this.id = 0;
        this.name = "";
        this.email = "";
        this.code = "";
        this.photo = 0;
        this.statuses = "";
        this.academic_awards = "";
        this.academic_rank = 0;
        this.academic_degree = 0;
        this.upqualification = "";
        this.phone_work = "";
        this.bio = "";
        this.roles = new ArrayList<>();
        this.black = new BlackList();
        this.friend = new FriendList();
    }

    public UserInterface extractUser() {
        Teacher.TeacherBuilder teacherBuilder = new Teacher.TeacherBuilder();
        teacherBuilder.id(id);
        teacherBuilder.name(name);
        teacherBuilder.email(email);
        teacherBuilder.code(code);
        teacherBuilder.photo(photo);
        teacherBuilder.statuses(statuses);
        teacherBuilder.academic_awards(academic_awards);
        teacherBuilder.academic_rank(academic_rank);
        teacherBuilder.academic_degree(academic_degree);
        teacherBuilder.upqualification(upqualification);
        teacherBuilder.phone_work(phone_work);
        teacherBuilder.bio(bio);
        teacherBuilder.friend(friend);
        teacherBuilder.black(black);
        List<Role> userRole = new ArrayList<>();
        for (UserRoleRow role: roles) {
            if (role.getType() == User.USER_TYPE_TEACHER) {
                userRole.add(role.extractTeacherRole());
            } else if (role.getType() == User.USER_TYPE_STUDENT) {
                userRole.add(role.extractStudentRole());
            }
        }
        teacherBuilder.roles(userRole);
        return new Teacher(teacherBuilder);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public int getPhoto() {
        return photo;
    }

    public String getStatuses() {
        return statuses;
    }

    public String getAcademic_awards() {
        return academic_awards;
    }

    public int getAcademic_rank() {
        return academic_rank;
    }

    public int getAcademic_degree() {
        return academic_degree;
    }

    public String getUpqualification() {
        return upqualification;
    }

    public String getPhone_work() {
        return phone_work;
    }

    public String getBio() {
        return bio;
    }

    public List<UserRoleRow> getRoles() {
        return roles;
    }

    public BlackList getBlack() {
        return black;
    }

    public FriendList getFriend() {
        return friend;
    }
}
