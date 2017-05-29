package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class RoleInfoTeacherRankItem {

    private final int question;
    private final int hint;
    private final double score;

    public RoleInfoTeacherRankItem(int question, int hint, double score) {
        this.question = question;
        this.hint = hint;
        this.score = score;
    }

    public int getQuestion() {
        return question;
    }

    public int getHint() {
        return hint;
    }

    public double getScore() {
        return score;
    }
}
