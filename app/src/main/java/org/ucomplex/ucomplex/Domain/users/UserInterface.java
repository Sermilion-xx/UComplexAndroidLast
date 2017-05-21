package org.ucomplex.ucomplex.Domain.users;

import org.ucomplex.ucomplex.Domain.BlackList;
import org.ucomplex.ucomplex.Domain.FriendList;
import org.ucomplex.ucomplex.Domain.role.Role;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 10/11/2016.
 * Project: uComplex_v_2
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public interface UserInterface {
    int getType();
    String getLogin();
    String getPassword();
    List<? extends Role> getRoles();
    int getPhoto();
    String getCode();
    String getName();
    int getId();
    int getPerson();
    FriendList getIsFriend();
    BlackList getIsBlack();
}
