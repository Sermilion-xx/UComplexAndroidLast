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

    private final String question;
    private final String hint;
    private final int score;

    public RoleInfoTeacherRankItem(String question, String hint, int score) {
        this.question = question;
        this.hint = hint;
        this.score = score;
    }

    public String getQuestion() {
        return question;
    }

    public String getHint() {
        return hint;
    }

    public int getScore() {
        return score;
    }
}
