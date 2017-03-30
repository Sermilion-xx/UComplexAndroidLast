package org.ucomplex.ucomplex;

import com.google.gson.Gson;

import org.junit.Test;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Modules.Subject.SubjectModel;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectRaw;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectModelTest {

    private static final String FILE_NAME = "subject.json";

    private SubjectRaw getSubjectRawfromJson() {
        String json = FacadeCommon.readFile(FILE_NAME);
        Gson gson = new Gson();
        return gson.fromJson(json, SubjectRaw.class);
    }

    @Test
    public void jsonStringToSubjectRaw() {
        SubjectRaw subjectRaw = getSubjectRawfromJson();
        assertThat("SubjectRaw must not be null", subjectRaw != null);
    }

    @Test
    public void getMarkReturnsMark() {
        SubjectModel model = SubjectModel.createTestModel();
        model.processData(getSubjectRawfromJson());
        assertThat("Wrong number of generated items. Must be: 4 and 40.", model.getData().getProfileItems().size() == 4 &&
                model.getData().getMaterialsItems().size() == 40);
    }

}
