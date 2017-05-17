package org.ucomplex.ucomplex.Modules.UserProfile.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 17/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class ResponseAddFriend {

    private final boolean is_friend;

    public ResponseAddFriend(boolean is_friend) {
        this.is_friend = is_friend;
    }

    public boolean is_friend() {
        return is_friend;
    }
}
