package org.ucomplex.ucomplex.Common;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.amulyakhare.textdrawable.TextDrawable;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Domain.users.UserInterface;
import org.ucomplex.ucomplex.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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


    /**
     * A copy of the Android internals  saveBitmapToStorage method, this method populates the
     * meta data with DATE_ADDED and DATE_TAKEN. This fixes a common problem where media
     * that is inserted manually gets saved at the end of the gallery (because date is not populated).
     * @see android.provider.MediaStore.Images.Media#insertImage(ContentResolver, Bitmap, String, String)
     */
    public static final String saveBitmapToStorage(ContentResolver cr,
                                                   Bitmap source,
                                                   String title,
                                                   String description) {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, title);
        values.put(MediaStore.Images.Media.DESCRIPTION, description);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        // Add the date meta data to ensure the image is added at the front of the gallery
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());

        Uri url = null;
        String stringUrl = null;    /* value to be returned */

        try {
            url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (source != null) {
                OutputStream imageOut = cr.openOutputStream(url);
                try {
                    source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
                } finally {
                    imageOut.close();
                }

                long id = ContentUris.parseId(url);
                // Wait until MINI_KIND thumbnail is generated.
                Bitmap miniThumb = MediaStore.Images.Thumbnails.getThumbnail(cr, id, MediaStore.Images.Thumbnails.MINI_KIND, null);
                // This is for backward compatibility.
                storeThumbnail(cr, miniThumb, id, 50F, 50F, MediaStore.Images.Thumbnails.MICRO_KIND);
            } else {
                cr.delete(url, null, null);
                url = null;
            }
        } catch (Exception e) {
            if (url != null) {
                cr.delete(url, null, null);
                url = null;
            }
        }

        if (url != null) {
            stringUrl = url.toString();
        }

        return stringUrl;
    }

    /**
     * A copy of the Android internals StoreThumbnail method, it used with the saveBitmapToStorage to
     * populate the android.provider.MediaStore.Images.Media#saveBitmapToStorage with all the correct
     * meta data. The StoreThumbnail method is private so it must be duplicated here.
     * @see android.provider.MediaStore.Images.Media (StoreThumbnail private method)
     */
    private static final Bitmap storeThumbnail(
            ContentResolver cr,
            Bitmap source,
            long id,
            float width,
            float height,
            int kind) {

        // create the matrix to scale it
        Matrix matrix = new Matrix();

        float scaleX = width / source.getWidth();
        float scaleY = height / source.getHeight();

        matrix.setScale(scaleX, scaleY);

        Bitmap thumb = Bitmap.createBitmap(source, 0, 0,
                source.getWidth(),
                source.getHeight(), matrix,
                true
        );

        ContentValues values = new ContentValues(4);
        values.put(MediaStore.Images.Thumbnails.KIND,kind);
        values.put(MediaStore.Images.Thumbnails.IMAGE_ID,(int)id);
        values.put(MediaStore.Images.Thumbnails.HEIGHT,thumb.getHeight());
        values.put(MediaStore.Images.Thumbnails.WIDTH,thumb.getWidth());

        Uri url = cr.insert(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, values);

        try {
            OutputStream thumbOut = cr.openOutputStream(url);
            thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut);
            thumbOut.close();
            return thumb;
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    public static File getOutputMediaFile(int type, String directory, String fileName) {
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

    public static Bitmap getBitmapFromStorage(Uri uri, Context activity, int... thumbnail_size) throws IOException {
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

    public static Uri createFileForBitmap() {
        File bitmapFile = FacadeMedia.getOutputMediaFile(MEDIA_TYPE_IMAGE, Environment.DIRECTORY_PICTURES, UCOMPLEX_PROFILE);
        if (bitmapFile != null) {
            return Uri.fromFile(bitmapFile);
        } else {
            throw new NullPointerException("Bitmap file is null");
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap bitmapRounded = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapRounded);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight())), pixels, pixels, paint);
        return bitmapRounded;
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

    public static String getFileNameFromUri(Uri uri, Context context) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
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
