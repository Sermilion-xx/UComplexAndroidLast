package org.ucomplex.ucomplex.Modules.Subject;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import static org.ucomplex.ucomplex.Modules.Subject.SubjectActivity.EXTRA_GCOURSE;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 31/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectTimelineFragment extends Fragment {

    public static SubjectTimelineFragment getInstance(int gcourse) {
        SubjectTimelineFragment fragment = new SubjectTimelineFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_GCOURSE, gcourse);
        fragment.setArguments(bundle);
        return fragment;
    }
}
