package org.ucomplex.ucomplex.Domain.Users.role.RoleExtractorFactory;

import org.ucomplex.ucomplex.Domain.Users.role.Role;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 20/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleExtractorFactory {

    public static Role extractRole(AbstractRoleExtractorFactory factory) {
        return factory.extractRole();
    }
}
