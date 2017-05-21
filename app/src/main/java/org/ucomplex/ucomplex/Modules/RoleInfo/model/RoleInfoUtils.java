package org.ucomplex.ucomplex.Modules.RoleInfo.model;

import android.content.Context;

import org.ucomplex.ucomplex.R;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 20/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleInfoUtils {

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

    public static String getDegree(int degree){
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

    public static String[] ranks = new String[]{"доцент","профессор","член-корреспондент", "академик"};

    public static String getRank(int degree){
        switch (degree) {
            case 1:
                return ranks[0];
            case 2:
                return ranks[1];
            case 3:
                return ranks[2];
            case 4:
                return ranks[4];
            default:return "не указанно";
        }
    }

    public static String getStudyForm(Context context, int type) {
        String typeStr = null;
        if (type == 1) {
            return "Очная";
        } else if (type == 2) {
            return "Заочная";
        } else if (type == 3) {
            return "Очно-заочная";
        } else if (type == 4) {
            return "Второе высшее";
        }
        return "";
    }

    public static String getPayment(Context context, int type) {
        String typeStr = null;
        if (type == 1) {
            return "Бюджет";
        } else if (type == 2) {
            return "Внебюджет";
        } else if (type == 3) {
            return "Бюджет целев";
        } else if (type == 4) {
            return "Льготы";
        }
        return "";
    }

    public static String getStudyLevel(Context context, int type) {
        if (type == 1) {
            return "Бакалавриат";
        } else if (type == 2) {
            return "Специалитет";
        } else if (type == 3) {
            return "Магистратура";
        } else if (type == 4) {
            return "Аспирантура";
        }
        return "";
    }
}
