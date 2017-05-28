package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherInfo;

import android.content.Context;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoTeacherInfoItem;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.RoleInfoTeacherRaw;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.model.TimetableCourse;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleInfoTeacherInfoModel implements MVPModel<RoleInfoTeacherRaw, List<RoleInfoTeacherInfoItem>, Void> {

    private Context context = UCApplication.getInstance();
    private List<RoleInfoTeacherInfoItem> mData;

    @Override
    public Observable<RoleInfoTeacherRaw> loadData(Void params) {
        return new Observable<RoleInfoTeacherRaw>() {
            @Override
            protected void subscribeActual(Observer<? super RoleInfoTeacherRaw> observer) {
                observer.onNext(RoleInfoTeacherRaw.getInstance());
            }
        };
    }

    @Override
    public void setData(List<RoleInfoTeacherInfoItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<RoleInfoTeacherInfoItem> data) {
        this.mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<RoleInfoTeacherInfoItem> getData() {
        return mData;
    }

    @Override
    public List<RoleInfoTeacherInfoItem> processData(RoleInfoTeacherRaw data) {
        mData = new ArrayList<>();

        RoleInfoTeacherInfoItem courses = new RoleInfoTeacherInfoItem(context.getString(R.string.teaching_disciplines), data.getCourses());
        mData.add(courses);

        StringBuilder disciplinesInTimetableBuilder = new StringBuilder();
        for (TimetableCourse timetableCourse: data.getTimetable_courses()) {
            disciplinesInTimetableBuilder.append(timetableCourse.getName()).append("\n");
        }
        RoleInfoTeacherInfoItem disciplinesInTimetable = new RoleInfoTeacherInfoItem(context.getString(R.string.disciplines_in_timetable), disciplinesInTimetableBuilder.toString());
        mData.add(disciplinesInTimetable);

        RoleInfoTeacherInfoItem rank = new RoleInfoTeacherInfoItem(context.getString(R.string.study_rank), getRank(data.getRank()));
        mData.add(rank);

        RoleInfoTeacherInfoItem degree = new RoleInfoTeacherInfoItem(context.getString(R.string.study_degree), getDegree(data.getDegree()));
        mData.add(degree);

        RoleInfoTeacherInfoItem upqualification = new RoleInfoTeacherInfoItem(context.getString(R.string.upqualifications), data.getUpqualification());
        mData.add(upqualification);

        RoleInfoTeacherInfoItem bio = new RoleInfoTeacherInfoItem(context.getString(R.string.short_bio), data.getBio());
        mData.add(bio);

        return mData;
    }

    private String[] ranks = new String[]{context.getString(R.string.docent),
            context.getString(R.string.professor),
            context.getString(R.string.member_correspondent),
            context.getString(R.string.academic)};

    private String getRank(int degree){
        switch (degree) {
            case 1:
                return ranks[0];
            case 2:
                return ranks[1];
            case 3:
                return ranks[2];
            case 4:
                return ranks[4];
            default:return context.getString(R.string.not_specified);
        }
    }

    public static String[] degrees = new String[]{
            "Технических наук",
            "Физико-математических наук",
            "Филологических наук",
            "Экономических наук",
            "Педагогических наук",
            "Политических наук",
            "Биологических наук",
            "Сельскохозяйственных наук",
            "Ветеринарных наук",
            "Географических наук",
            "Юридических наук",
            "Исторических наук",
            "Философских наук",
            "Химических наук",
            "Медицинских наук",
            "Фармацевтических наук",
            "Социологических наук",
            "Психологических наук",
            "Геолого-минералогических наук",
            "Военных наук",
            "Архитектуры",
            "Искусствоведения",
            "Культурологии",
            "не указанно"};

    public String getDegree(int degree){
        switch (degree){
            case 1:return  degrees[0];
            case 2:return  degrees[1];
            case 3:return  degrees[2];
            case 4:return  degrees[3];
            case 5:return  degrees[4];
            case 6:return  degrees[5];
            case 7:return  degrees[6];
            case 8:return  degrees[7];
            case 9:return  degrees[8];
            case 10:return degrees[9];
            case 11:return degrees[10];
            case 12:return degrees[11];
            case 13:return degrees[12];
            case 14:return degrees[13];
            case 15:return degrees[14];
            case 16:return degrees[15];
            case 17:return degrees[16];
            case 18:return degrees[17];
            case 19:return degrees[18];
            case 20:return degrees[19];
            case 21:return degrees[20];
            case 22:return degrees[21];
            case 23:return degrees[22];
            default:return degrees[23];
        }
    }
}
