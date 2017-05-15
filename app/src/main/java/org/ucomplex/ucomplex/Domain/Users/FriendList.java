package org.ucomplex.ucomplex.Domain.Users;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 07/02/2017.
 * Project: uComplex_v_2
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class FriendList {

    private final boolean is_friend;
    private final boolean req_sent;

    public FriendList() {
        this.is_friend = false;
        this.req_sent = false;
    }

    public boolean is_friend() {
        return is_friend;
    }

    public boolean isReq_sent() {
        return req_sent;
    }

}
