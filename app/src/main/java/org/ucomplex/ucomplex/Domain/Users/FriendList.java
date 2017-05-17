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

    private boolean is_friend;
    private boolean req_sent;

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

    public void setIs_friend(boolean is_friend) {
        this.is_friend = is_friend;
    }

    public void setReq_sent(boolean req_sent) {
        this.req_sent = req_sent;
    }
}
