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
    private final Map<String, Map<String, Integer>> votes;
    private final int count;

    public RoleInfoTeacherRankRaw() {
        this.teacher = 0;
        this.votes = new HashMap<>();
        this.count = 0;
    }

    public int getTeacher() {
        return teacher;
    }

    public Map<String, Map<String, Integer>> getVotes() {
        return votes;
    }

    public int getCount() {
        return count;
    }
}
