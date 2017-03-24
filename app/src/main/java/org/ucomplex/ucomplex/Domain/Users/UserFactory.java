package org.ucomplex.ucomplex.Domain.Users;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UserFactory {

    public static UserInterface getUserForType(int type) {
        switch (type) {
            case 4:
                return new User();
            case 3:
                return new Teacher();
            default:
                return new User();
        }
    }
}
