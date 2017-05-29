package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model;

import java.util.HashMap;
import java.util.Map;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class RoleInfoTeacherRankRaw {

    private final int teacher;
    private final Map<String, Object> votes;

    public RoleInfoTeacherRankRaw() {
        this.teacher = 0;
        votes = new HashMap<>();
    }

    public int getTeacher() {
        return teacher;
    }

    public Map<String, Object> getVotes() {
        return votes;
    }
}
