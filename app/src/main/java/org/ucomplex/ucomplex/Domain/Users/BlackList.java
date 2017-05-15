package org.ucomplex.ucomplex.Domain.Users;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 07/02/2017.
 * Project: uComplex_v_2
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class BlackList {

    private final boolean me_black;
    private final boolean is_black;

    public BlackList() {
        this.me_black = false;
        this.is_black = false;
    }

    public boolean isMe_black() {
        return me_black;
    }

    public boolean is_black() {
        return is_black;
    }
}
