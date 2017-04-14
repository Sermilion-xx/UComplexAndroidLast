package org.ucomplex.ucomplex.Modules.Users.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 13/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UsersParams {

    private UserRequestType requestType;
    private boolean isFriend;
    private boolean isBlocked;
    private int start;

    public UserRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(UserRequestType requestType) {
        this.requestType = requestType;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
