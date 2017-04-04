package org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectTimelineParams {
    private int gcourse;
    private int start;

    public SubjectTimelineParams() {
        this.start = 0;
    }

    public int getGcourse() {
        return gcourse;
    }

    public void setGcourse(int gcourse) {
        this.gcourse = gcourse;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
