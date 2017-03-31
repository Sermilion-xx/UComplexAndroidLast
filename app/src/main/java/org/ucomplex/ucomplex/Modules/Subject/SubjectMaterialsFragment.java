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

public class SubjectMaterialsFragment extends Fragment {

    public static SubjectMaterialsFragment getInstance(int gcourse) {
        SubjectMaterialsFragment fragment = new SubjectMaterialsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_GCOURSE, gcourse);
        fragment.setArguments(bundle);
        return fragment;
    }
}
