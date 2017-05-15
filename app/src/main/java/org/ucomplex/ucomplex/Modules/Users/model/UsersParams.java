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

public final class UsersParams {

    private final UserRequestType requestType;
    private final int start;

    private UsersParams(UserRequestType requestType, int start) {
        this.requestType = requestType;
        this.start = start;
    }

    public static UsersParams createLoadMoreParams(UserRequestType requestType, int start) {
        return new UsersParams(requestType, start);
    }

    public UserRequestType getRequestType() {
        return requestType;
    }

    public int getStart() {
        return start;
    }

}
