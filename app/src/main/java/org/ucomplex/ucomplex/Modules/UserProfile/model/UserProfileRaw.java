package org.ucomplex.ucomplex.Modules.UserProfile.model;

import org.ucomplex.ucomplex.Domain.Users.BlackList;
import org.ucomplex.ucomplex.Domain.Users.FriendList;
import org.ucomplex.ucomplex.Domain.Users.Teacher;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.Domain.Users.role.Role;
import org.ucomplex.ucomplex.Domain.Users.role.RoleBase;
import org.ucomplex.ucomplex.Domain.Users.role.RoleStudent;
import org.ucomplex.ucomplex.Domain.Users.role.RoleTeacher;

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

    private UserProfileRaw() {
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

    public final UserInterface extractUser() {
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
                userRole.add(roleStudentStrategy.apply(role));
            } else if (role.getType() == User.USER_TYPE_STUDENT) {
                userRole.add(roleTeacherStrategy.apply(role));
            }
        }
        teacherBuilder.roles(userRole);
        return new Teacher(teacherBuilder);
    }

    private final Function<RoleStudent, UserRoleRow> roleStudentStrategy = role -> {
        RoleStudent.RoleStudentBuilder roleStudentBuilder = new RoleStudent.RoleStudentBuilder();
        roleStudentBuilder.id(role.getId());
        roleStudentBuilder.person(role.getPerson());
        roleStudentBuilder.type(role.getType());
        roleStudentBuilder.name(role.getName());
        roleStudentBuilder.role(role.getRole());
        roleStudentBuilder.position(role.getPosition());
        roleStudentBuilder.position_name(role.getPosition_name());

        roleStudentBuilder.group(role.getGroup());
        roleStudentBuilder.major(role.getMajor());
        roleStudentBuilder.study(role.getStudy());
        roleStudentBuilder.year(role.getYear());
        roleStudentBuilder.payment(role.getPayment());
        roleStudentBuilder.contract_year(role.getContract_year());
        return new RoleStudent(roleStudentBuilder);
    };

    private final Function<RoleTeacher, UserRoleRow> roleTeacherStrategy = role -> {
        RoleTeacher.RoleTeacherBuilder roleTeacherBuilder = new RoleTeacher.RoleTeacherBuilder();
        roleTeacherBuilder.id(role.getId());
        roleTeacherBuilder.person(role.getPerson());
        roleTeacherBuilder.type(role.getType());
        roleTeacherBuilder.name(role.getName());
        roleTeacherBuilder.role(role.getRole());
        roleTeacherBuilder.position(role.getPosition());
        roleTeacherBuilder.position_name(role.getPosition_name());

        roleTeacherBuilder.rate(role.getRate());
        roleTeacherBuilder.employment_type(role.getEmployment_type());
        roleTeacherBuilder.public_role(role.getPublic_role());
        roleTeacherBuilder.login(role.getLogin());
        roleTeacherBuilder.photo(role.getPhoto());
        roleTeacherBuilder.code(role.getCode());
        roleTeacherBuilder.email(role.getEmail());
        roleTeacherBuilder.alias(role.getAlias());
        roleTeacherBuilder.section(role.getSection());
        roleTeacherBuilder.section_name(role.getSection_name());
        roleTeacherBuilder.lead(role.getLead());
        roleTeacherBuilder._public(role.get_public());
        return new RoleTeacher(roleTeacherBuilder);
    };
}
