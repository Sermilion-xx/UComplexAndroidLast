package org.ucomplex.ucomplex.Modules.Subject;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.Subject.Files;
import org.ucomplex.ucomplex.Domain.Users.File;
import org.ucomplex.ucomplex.Domain.Users.Teacher;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemFile;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectItemProfile;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectObject;
import org.ucomplex.ucomplex.Modules.Subject.model.SubjectRaw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectModel implements MVPModel<SubjectRaw, SubjectObject, Integer> {

    private SubjectObject mData;
    private SubjectService subjectService;

    public SubjectModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    private SubjectModel(boolean test) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    public static SubjectModel createTestModel() {
        return new SubjectModel(true);
    }


    @Inject
    public void setSubjectService(SubjectService service) {
        this.subjectService = service;
    }

    @Override
    public Observable<SubjectRaw> loadData(Integer params) {
        return null;
    }

    @Override
    public void setData(SubjectObject data) {
        mData = data;
    }

    @Override
    public void addData(SubjectObject data) {
        throw new UnsupportedOperationException("Adding new data is not supported");
    }

    @Override
    public void clear() {
        mData = null;
    }

    @Override
    public SubjectObject getData() {
        return mData;
    }

    @Override
    public void processData(SubjectRaw data) {
        mData = new SubjectObject();
        Map<Integer, Teacher> teachers = new HashMap<>();
        Set<SubjectItemProfile> profileItems = new HashSet<>();
        List<SubjectItemFile> filesItems = new ArrayList<>();

        Teacher mainTeacher = data.getTeacher();
        teachers.put(mainTeacher.getId(), mainTeacher);
        profileItems.add(extractProfileItem(mainTeacher));

        SubjectItemProfile attendanceItem = new SubjectItemProfile();
        double absence = getAbsence(data.getProgress().getAbsence(), data.getProgress().getHours());
        absence = FacadeCommon.round(absence, 2);
        attendanceItem.setAttendance(Double.toString(absence));
        profileItems.add(attendanceItem);

        SubjectItemProfile markItem = new SubjectItemProfile();
        double mark = getMark(data.getProgress().getMark(), data.getProgress().getMarksCount());
        mark = FacadeCommon.round(mark, 2);
        markItem.setMark(Double.toString(mark));
        profileItems.add(markItem);

        for (int i = 0; i < data.getFiles().size(); i++) {
            Files files = data.getFiles().get(i);
            profileItems.add(extractProfileItem(files.getTeacher()));
            teachers.put(files.getTeacher().getId(), files.getTeacher());
            for (File file : files.getFiles()) {
                filesItems.add(extractFileItem(file, teachers));
            }
        }
        mData.setMaterialsItems(filesItems);
        mData.setProfileItems(new ArrayList<>(profileItems));
    }

    private SubjectItemFile extractFileItem(File file, Map<Integer, Teacher> teachers) {
        SubjectItemFile item = new SubjectItemFile();
        item.setAddress(file.getAddress());
        item.setName(file.getName());
        item.setData(file.getData());
        item.setOwnersName(teachers.get(file.getOwner()).getName());
        item.setSize(file.getSize());
        return item;
    }

    private SubjectItemProfile extractProfileItem(Teacher teacher) {
        SubjectItemProfile profileItem = new SubjectItemProfile();
        profileItem.setName(teacher.getName());
        profileItem.setId(teacher.getId());
        profileItem.setCode(teacher.getCode());
        return profileItem;
    }

    private double getMark(int progressMark, int markCount) {
        double mark = 0.0;
        if (progressMark != 0 && markCount != 0) {
            mark = progressMark / markCount;
        }
        return mark;
    }

    private double getAbsence(int progressAbsence, int hours) {
        double absence = 100;
        if (progressAbsence != 0 && hours != 0) {
            absence = ((double) progressAbsence / (double) hours) * 100;
            if (absence == 0.0) {
                absence = 100;
            }
        }
        return absence;
    }
}
