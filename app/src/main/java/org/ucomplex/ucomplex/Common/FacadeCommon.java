package org.ucomplex.ucomplex.Common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.widget.Toast;

import org.ucomplex.ucomplex.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 24/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class FacadeCommon {

    public static String encodeLoginData(String loginData) {
        byte[] authBytes;
        try {
            authBytes = loginData.getBytes("UTF-8");
            int flags = Base64.NO_WRAP | Base64.URL_SAFE;
            return Base64.encodeToString(authBytes, flags);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    public static String getStringUserType(Context context, int type) {
        String typeStr = null;
        if (type == 0) {
            typeStr = context.getResources().getString(R.string.sotrudnik);
        }
        if (type == 1) {
            typeStr = context.getResources().getString(R.string.administrator);
        }
        if (type == 2) {
            typeStr = context.getResources().getString(R.string.sub_administrator);
        } else if (type == 3) {
            typeStr = context.getResources().getString(R.string.prepodvatel);
        } else if (type == 4) {
            typeStr = context.getResources().getString(R.string.student);
        } else if (type == 5) {
            typeStr = context.getResources().getString(R.string.metodist_po_raspisaniyu);
        } else if (type == 6) {
            typeStr = context.getResources().getString(R.string.metodist_ko);
        } else if (type == 7) {
            typeStr = context.getResources().getString(R.string.bibliotekar);
        } else if (type == 8) {
            typeStr = context.getResources().getString(R.string.tehsekretar);
        } else if (type == 9) {
            typeStr = context.getResources().getString(R.string.abiturient);
        } else if (type == 10) {
            typeStr = context.getResources().getString(R.string.uchebny_otdel);
        } else if (type == 11) {
            typeStr = context.getResources().getString(R.string.rukovoditel);
        } else if (type == 12) {
            typeStr = context.getResources().getString(R.string.monitoring);
        } else if (type == 13) {
            typeStr = context.getResources().getString(R.string.dekan);
        } else if (type == 14) {
            typeStr = context.getResources().getString(R.string.otdel_kadrov);
        }
        return typeStr;
    }

    public static String makeHumanReadableDate(String time, boolean... justDate) {
        String r = "";
        String yyyyMMdd = time.split(" ")[0];
        String hhMMss = null;
        if (time.length() == 2) {
            hhMMss = time.split(" ")[1];
        }
        try {
            Locale locale = new Locale("ru", "RU");
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale).parse(time);
            Date today = new Date();

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(today);
            int day1 = cal1.get(Calendar.DAY_OF_MONTH);
            int month1 = cal1.get(Calendar.MONTH);
            int year1 = cal1.get(Calendar.YEAR);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date);
            int month2 = cal2.get(Calendar.MONTH);
            int year2 = cal2.get(Calendar.YEAR);
            int day2 = cal2.get(Calendar.DAY_OF_MONTH);

            if (day1 == day2 && month1 == month2 && year1 == year2) {
                r += "Сегодня";
            } else if (day1 - 1 == day2 && month1 == month2 && year1 == year2) {
                r += "Вчера";
            } else {
                String[] tempYyMMdd = yyyyMMdd.split("-");
                String tempMonth = tempYyMMdd[1];
                String month = "";
                if (tempMonth.equals("01")) {
                    month = "января";
                }
                if (tempMonth.equals("02")) {
                    month = "февряля";
                }
                if (tempMonth.equals("03")) {
                    month = "марта";
                }
                if (tempMonth.equals("04")) {
                    month = "апреля";
                }
                if (tempMonth.equals("05")) {
                    month = "мая";
                }
                if (tempMonth.equals("06")) {
                    month = "июня";
                }
                if (tempMonth.equals("07")) {
                    month = "июля";
                }
                if (tempMonth.equals("08")) {
                    month = "августа";
                }
                if (tempMonth.equals("09")) {
                    month = "сентября";
                }
                if (tempMonth.equals("10")) {
                    month = "октября";
                }
                if (tempMonth.equals("11")) {
                    month = "ноября";
                }
                if (tempMonth.equals("12")) {
                    month = "декабря";
                }
                r += tempYyMMdd[2] + " " + month + " " + tempYyMMdd[0] + " г.";
            }
            if (justDate != null) {
                if (hhMMss != null) {
                    r += " в " + hhMMss.substring(0, 5);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (r.equals("")) {
            r = yyyyMMdd;
        }
        return r;
    }

    public static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static boolean checkStoragePermissions(Activity activity) {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : PERMISSIONS_STORAGE) {
            result = ContextCompat.checkSelfPermission(activity, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_EXTERNAL_STORAGE);
            return false;
        }
        return true;
    }

    public static int getAvailableListLayout(int itemCount) {
        if (!UCApplication.getInstance().isConnectedToInternet() && itemCount == 0) {
            return R.layout.item_no_internet;
        } else if (itemCount == 0) {
            return R.layout.item_no_content;
        } else {
            return Constants.CUSTOM_ADAPTER_ITEM_LAYOUT_AVAILABLE;
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd;
        try{
            bd = new BigDecimal(value);
        }catch (NumberFormatException e){
            return 0.0;
        }
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String readFile(String name) {
        StringBuilder builder = new StringBuilder();
        File file = new File(name);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                builder.append(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ignored) {
            }
        }
        return builder.toString();
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    public static String readableFileSize(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " б";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "кмгтпе" : "кмгтпе").charAt(exp - 1) + (si ? "" : "");
        return String.format(new Locale("Ru"),"%.1f %sб", bytes / Math.pow(unit, exp), pre);
    }

    public static void requireStoragePermission(Context context) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, context.getString(R.string.need_storage_permissions), Toast.LENGTH_LONG).show();
        }
    }

}
