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

public final class UserProfileItem{

    private final String personName;
    private final String roleName;
    private FriendList friend;
    private BlackList blocked;

    private final String positionName;
    private final String disciplineName;

    private final String code;
    private final int id;

    public UserProfileItem(String personName,
                           String roleName,
                           FriendList friend,
                           BlackList blocked,
                           String code,
                           int id) {
        this.personName = personName;
        this.roleName = roleName;
        this.friend = friend;
        this.blocked = blocked;
        this.code = code;
        this.id = id;
        this.positionName = "";
        this.disciplineName = "";
    }

    public UserProfileItem(String positionName, String sectionName) {
        this.positionName = positionName;
        this.disciplineName = sectionName;
        this.personName = "";
        this.roleName = "";
        this.code = "";
        this.id = -1;
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

    public FriendList getFriend() {
        return friend;
    }

    public BlackList getBlocked() {
        return blocked;
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public void setFriend(FriendList friend) {
        this.friend = friend;
    }

    public void setBlocked(boolean blocked) {
        this.blocked.setIs_black(blocked);
    }
}
