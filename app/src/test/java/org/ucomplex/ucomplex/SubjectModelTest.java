package org.ucomplex.ucomplex;

import com.google.gson.Gson;

import org.junit.Test;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.SubjectModel;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model.SubjectRaw;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.SubjectTimelineModel;
import org.ucomplex.ucomplex.Modules.Subject.SubjectTimeline.model.SubjectTimelineRaw;
import org.ucomplex.ucomplex.Modules.SubjectsList.SubjectsListModel;
import org.ucomplex.ucomplex.Modules.SubjectsList.model.SubjectsListRaw;

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

    private static final String SUBJECT_JSON = "subject.json";
    private static final String SUBJECT_TIMELINE_JSON = "subject_timeline.json";
    private static final String SUBJECTS_LIST_JSON = "subjects_list.json";

    //------------------------------- SubjectModel ------------------------------------------------/

    private SubjectRaw getSubjectRawFromJson(String fileName) {
        String json = FacadeCommon.readFile(fileName);
        Gson gson = new Gson();
        return gson.fromJson(json, SubjectRaw.class);
    }

    @Test
    public void jsonStringToSubjectRaw() {
        SubjectRaw subjectRaw = getSubjectRawFromJson(SUBJECT_JSON);
        assertThat("SubjectRaw must not be null", subjectRaw != null);
    }

    @Test
    public void getMarkReturnsMark() {
        SubjectModel model = SubjectModel.createTestModel();
        model.processData(getSubjectRawFromJson(SUBJECT_JSON));
        assertThat("Wrong number of generated items. Must be: 4 and 40.", model.getData().getProfileItems().size() == 4 &&
                model.getData().getMaterialsItems().size() == 40);
    }

    //------------------------------- SubjectTimeline ---------------------------------------------/

    private SubjectTimelineRaw getSubjectTimelineRawFromJson(String fileName) {
        String json = FacadeCommon.readFile(fileName);
        Gson gson = new Gson();
        return gson.fromJson(json, SubjectTimelineRaw.class);
    }

    @Test
    public void jsonStringToSubjectTimelineRaw() {
        SubjectTimelineRaw subjectRaw = getSubjectTimelineRawFromJson(SUBJECT_TIMELINE_JSON);
        assertThat("SubjectTimelineRaw fields must not be null",
                subjectRaw.getCourses() != null &&
                subjectRaw.getGcourse() != null &&
                subjectRaw.getMarks() != null &&
                subjectRaw.getTeachers() != null &&
                subjectRaw.getUser() != null);
    }

    @Test
    public void SubjectTimelineRawToSubjectTimelineListItems() {
        SubjectTimelineRaw raw = getSubjectTimelineRawFromJson(SUBJECT_TIMELINE_JSON);
        SubjectTimelineModel model = SubjectTimelineModel.getTestInstance();
        model.processData(raw);
        assertThat("Model data should not be null", model.getData() != null);
    }

    //------------------------------- SubjectsList ------------------------------------------------/

    private SubjectsListRaw getSubjectsListRawFromJson(String fileName) {
        String json = FacadeCommon.readFile(fileName);
        Gson gson = new Gson();
        return gson.fromJson(json, SubjectsListRaw.class);
    }

    @Test
    public void jsonStringToSubjectsListRaw() {
        SubjectsListRaw subjectRaw = getSubjectsListRawFromJson(SUBJECTS_LIST_JSON);
        assertThat("SubjectsListRaw fields must not be null",
                subjectRaw.getCourses() != null &&
                        subjectRaw.getCourses_forms() != null &&
                        subjectRaw.getStudentSubjectsList() != null);
    }

    @Test
    public void SubjectsListRawToSubjectListItems() {
        SubjectsListRaw raw = getSubjectsListRawFromJson(SUBJECTS_LIST_JSON);
        SubjectsListModel model = SubjectsListModel.getTestInstance();
        model.processData(raw);
        assertThat("Model data should not be null", model.getData() != null);
    }
}
