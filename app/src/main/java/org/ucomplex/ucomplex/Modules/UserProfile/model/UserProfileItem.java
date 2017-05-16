package org.ucomplex.ucomplex.Modules.UserProfile.model;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Domain.Users.BlackList;
import org.ucomplex.ucomplex.Domain.Users.FriendList;
import org.ucomplex.ucomplex.Domain.Users.User;
import org.ucomplex.ucomplex.Domain.Users.role.Role;
import org.ucomplex.ucomplex.Domain.Users.role.RoleTeacher;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 03/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UserProfileItem {

    private final String personName;
    private final String roleName;
    private final FriendList friend;
    private final BlackList blocked;
    private final String positionName;
    private final String disciplineName;

    public UserProfileItem(String personName,
                           String roleName,
                           FriendList friend,
                           BlackList blocked) {
        this.personName = personName;
        this.roleName = roleName;
        this.friend = friend;
        this.blocked = blocked;
        this.positionName = "";
        this.disciplineName = "";
    }

    public UserProfileItem(Role role) {
        if (role.getType() == User.USER_TYPE_STUDENT) {
            this.positionName = FacadeCommon.getStringUserType(UCApplication.getInstance(), role.getType());
            this.disciplineName = role.getPosition_name();
        } else {
            this.positionName = role.getPosition_name();
            this.disciplineName = ((RoleTeacher)role).getSection_name();
        }
        this.personName = "";
        this.roleName = "";
        this.friend = new FriendList();
        this.blocked = new BlackList();

    }

    public String getPersonName() {
        return personName;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getPositionName() {
        return positionName;
    }

    public String getDisciplineName() {
        return disciplineName;
    }
}
