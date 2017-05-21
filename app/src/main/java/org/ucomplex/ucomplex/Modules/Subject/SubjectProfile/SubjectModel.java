package org.ucomplex.ucomplex.Modules.Subject.SubjectProfile;

import android.support.v4.util.Pair;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.MaterialsFile;
import org.ucomplex.ucomplex.Domain.role.Role;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model.FilesRaw;
import org.ucomplex.ucomplex.Domain.role.RoleTeacher;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectItemFile;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model.SubjectItemProfile;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model.SubjectObject;
import org.ucomplex.ucomplex.Modules.Subject.SubjectProfile.model.SubjectRaw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    private Map<Integer, Role> mTeachers;
    private SubjectObject mData;
    private SubjectService subjectService;

    public SubjectModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
    }

    private SubjectModel(boolean test) {

    }

    public static SubjectModel createTestModel() {
        return new SubjectModel(true);
    }

    @Inject
    public void setSubjectService(SubjectService service) {
        this.subjectService = service;
    }

    @Override
    public Observable<SubjectRaw> loadData(Integer gcourse) {
        return subjectService.getSubject(gcourse).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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

    public Pair<List<SubjectItemFile>, Map<Integer, Role>> getFilesAndTeachers() {
        if (mData != null) {
            return new Pair<>(mData.getMaterialsItems(), mTeachers);
        }
        return null;
    }

    @Override
    public SubjectObject processData(SubjectRaw data) {
        mData = new SubjectObject();
        mTeachers = new HashMap<>();

        Set<SubjectItemProfile> profileItemsSet = new HashSet<>();
        List<SubjectItemFile> filesItems = new ArrayList<>();

        RoleTeacher mainTeacher = (RoleTeacher) data.getTeacher();
        mTeachers.put(mainTeacher.getId(), mainTeacher);
        profileItemsSet.add(extractProfileItem(mainTeacher));

        for (int i = 0; i < data.getFiles().size(); i++) {
            FilesRaw filesRaw = data.getFiles().get(i);
            profileItemsSet.add(extractProfileItem(filesRaw.getTeacher()));
            mTeachers.put(filesRaw.getTeacher().getId(), filesRaw.getTeacher());
            if (filesRaw.getFiles() != null) {
                for (MaterialsFile materialsFile : filesRaw.getFiles()) {
                    filesItems.add(extractFileItem(materialsFile, mTeachers));
                }
            }
        }

        List<SubjectItemProfile> profileItemsList = new ArrayList<>();
        SubjectItemProfile header = new SubjectItemProfile();
        header.setName(data.getCourse().getName());
        profileItemsList.add(header);
        profileItemsList.addAll(new ArrayList<>(profileItemsSet));

        SubjectItemProfile markAttendanceItem = new SubjectItemProfile();
        if (data.getProgress() != null) {
            double mark = getMark(data.getProgress().getMark(), data.getProgress().getMarksCount());
            mark = FacadeCommon.round(mark, 2);
            markAttendanceItem.setMark(Double.toString(mark));

            double absence = getAbsence(data.getProgress().getAbsence(), data.getProgress().getHours());
            absence = FacadeCommon.round(absence, 2);
            markAttendanceItem.setAttendance(Double.toString(absence));
        }
        profileItemsList.add(markAttendanceItem);

        mData.setMaterialsItems(filesItems);
        mData.setProfileItems(profileItemsList);
        return mData;
    }

    private SubjectItemFile extractFileItem(MaterialsFile materialsFile, Map<Integer, Role> teachers) {
        SubjectItemFile item = new SubjectItemFile();
        item.setAddress(materialsFile.getAddress());
        item.setName(materialsFile.getName());
        item.setData(materialsFile.getData());
        item.setOwnersName(teachers.get(materialsFile.getOwner()).getName());
        item.setSize(materialsFile.getSize());
        item.setType(materialsFile.getType());
        item.setTime(materialsFile.getTime());
        return item;
    }

    private SubjectItemProfile extractProfileItem(Role teacher) {
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
