package org.ucomplex.ucomplex.Domain.Users.role;

import android.os.Parcelable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 09/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface Role {
    int getId();
    int getPerson();
    int getType();
    String getName();
    int getRole();
    int getPosition();
}
