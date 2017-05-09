package org.ucomplex.ucomplex.Common;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.amulyakhare.textdrawable.TextDrawable;

import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 07/11/2016.
 * Project: uComplex_v_2
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class FacadeMedia {

    private static final String CONTENT_DOWNLOADS_PUBLIC_DOWNLOADS = "content://downloads/public_downloads";
    private static final String IMAGE = "image";
    private static final String VIDEO = "video";
    private static final String AUDIO = "audio";
    private static final String FILE = "file";
    private static final String CONTENT = "content";
    public static final String PACKAGE = "org.ucomplex.ucomplex.Modules.Materials";
    private static final String MEDIA_DOCUMENTS_PROVIDER = "com.android.providers.media.documents";
    private static final String DOWNLOADS_PROVIDER = "com.android.providers.downloads.documents";
    private static final String EXTERNAL_DOCUMENTS_PROVIDER = "com.android.externalstorage.documents";
    private static final String COLUMN_DATA = "_data";
    private static final String UCOMPLEX_PROFILE = "ucomplex_profile";

    private static File getOutputMediaFile(int type, String directory, String fileName) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                directory), "UComplex");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("UComplex", "failed to create directory");
            }
        }
        // Create a media file courseName
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    fileName);
        } else {
            return null;
        }
        return mediaFile;
    }

    private static int getPowerOfTwoForSampleRatio(Double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0)
            return 1;
        else
            return k;
    }

    public static Bitmap getThumbnail(Uri uri, Context activity, int... thumbnail_size) throws IOException {
        if (uri != null) {

            if (thumbnail_size.length == 0) {
                thumbnail_size = new int[1];
                thumbnail_size[0] = 640;
            }
            InputStream input = activity.getContentResolver().openInputStream(uri);
            BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
            onlyBoundsOptions.inJustDecodeBounds = true;
            onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
            BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
            input.close();
            if (onlyBoundsOptions.outWidth == -1 || onlyBoundsOptions.outHeight == -1)
                return null;
            int originalSize;
            if (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth)
                originalSize = onlyBoundsOptions.outHeight;
            else
                originalSize = onlyBoundsOptions.outWidth;

            Double ratio;
            if (originalSize > thumbnail_size[0])
                ratio = (double) originalSize / thumbnail_size[0];
            else
                ratio = 1.0;

            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
            bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
            input = activity.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
            if (input != null) {
                input.close();
            }
            return bitmap;
        }
        return null;
    }

    public static Drawable getTextDrawable(int personId, String name, Context context) {
        final int colorsCount = 16;
        final int number = (personId <= colorsCount) ? personId : personId % colorsCount;
        char firstLetter = name.split("")[1].charAt(0);
        return TextDrawable.builder().beginConfig()
                .width(60)
                .height(60)
                .endConfig()
                .buildRound(String.valueOf(firstLetter), ContextCompat.getColor(context, getColor(number)));
    }

    private static int getColor(int index) {
        int[] hexColors = {
                R.color.color_uc_placeholder1,
                R.color.color_uc_placeholder2,
                R.color.color_uc_placeholder3,
                R.color.color_uc_placeholder4,
                R.color.color_uc_placeholder5,
                R.color.color_uc_placeholder6,
                R.color.color_uc_placeholder7,
                R.color.color_uc_placeholder8,
                R.color.color_uc_placeholder9,
                R.color.color_uc_placeholder10,
                R.color.color_uc_placeholder11,
                R.color.color_uc_placeholder12,
                R.color.color_uc_placeholder13,
                R.color.color_uc_placeholder14,
                R.color.color_uc_placeholder15,
                R.color.color_uc_placeholder16

        };
        return hexColors[index];
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static Uri createFileForBitmap(String directory) {
        File bitmapFile = FacadeMedia.getOutputMediaFile(MEDIA_TYPE_IMAGE, Environment.DIRECTORY_PICTURES, UCOMPLEX_PROFILE);
        if (bitmapFile != null) {
            return Uri.fromFile(bitmapFile);
        } else {
            throw new NullPointerException("Bitmap file is null");
        }
    }

    public static Drawable getDrawable(UserInterface user) {
        final int colorsCount = 16;
        final int number = (user.getPerson() <= colorsCount) ? user.getPerson() : user.getPerson() % colorsCount;
        char firstLetter = user.getName().split(" ").length > 1 ? user.getName().split(" ")[1].charAt(0) : user.getName().split(" ")[0].charAt(0);

        return TextDrawable.builder().beginConfig()
                .width(120)
                .height(120)
                .endConfig()
                .buildRound(String.valueOf(firstLetter), getColor(number));
    }

    public static String getLetter(int mark) {
        if (mark == -1) {
            return "н";
        } else if (mark == 0) {
            return "\uF00C";
        } else if (mark == -3) {
            return "б";
        } else {
            return String.valueOf(mark);
        }
    }
}
