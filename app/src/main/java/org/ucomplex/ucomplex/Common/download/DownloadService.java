package org.ucomplex.ucomplex.Common.download;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 07/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */


import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.ServiceGenerator;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class DownloadService extends IntentService {

    private static final String EXTRA_URL = "extraUrl";
    private static final String EXTRA_NAME = "extraName";

    public static Intent createIntent(Context context, String url, String name) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_NAME, name);
        return intent;
    }

    private DownloadRetrofitService retrofitInterface;

    public DownloadService() {
        super("Download Service");
    }

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int totalFileSize;

    @Override
    protected void onHandleIntent(Intent intent) {
        String fileName = intent.getStringExtra(EXTRA_NAME);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_file_download)
                .setContentTitle(getString(R.string.downloading))
                .setContentText(fileName)
                .setAutoCancel(true);
        notificationManager.notify(0, notificationBuilder.build());
        initDownload(intent.getStringExtra(EXTRA_URL), fileName);
    }

    private void initDownload(String url, String name) {
        retrofitInterface = ServiceGenerator.createService(DownloadRetrofitService.class, UCApplication.getInstance().getAuthString());
        Call<ResponseBody> request = retrofitInterface.downloadFile(url);
        try {
            downloadFile(request.execute().body(), name);
        } catch (IOException e) {
            onDownloadFailed();
            e.printStackTrace();
        }
    }

    private void downloadFile(ResponseBody body, String name) throws IOException {
        int count;
        byte data[] = new byte[1024 * 4];
        long fileSize = body.contentLength();
        InputStream bis = new BufferedInputStream(body.byteStream(), 1024 * 8);
        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
        OutputStream output = new FileOutputStream(outputFile);
        long total = 0;
        long startTime = System.currentTimeMillis();
        int timeCount = 1;
        while ((count = bis.read(data)) != -1) {
            total += count;
            totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
            double current = Math.round(total / (Math.pow(1024, 2)));
            int progress = (int) ((total * 100) / fileSize);
            long currentTime = System.currentTimeMillis() - startTime;
            Download download = new Download();
            download.setTotalFileSize(totalFileSize);
            if (currentTime > 1000 * timeCount) {
                download.setCurrentFileSize((int) current);
                download.setProgress(progress);
                sendNotification(download);
                timeCount++;
            }
            output.write(data, 0, count);
        }
        onDownloadComplete();
        output.flush();
        output.close();
        bis.close();
    }

    private void sendNotification(Download download) {
        notificationBuilder.setProgress(100, download.getProgress(), false);
        notificationBuilder.setContentText("Downloading file " + download.getCurrentFileSize() + "/" + totalFileSize + " MB");
        notificationManager.notify(0, notificationBuilder.build());
    }

    private void onDownloadComplete() {
        Download download = new Download();
        download.setProgress(100);
        notificationManager.cancel(0);
        notificationBuilder.setProgress(0, 0, false);
        notificationBuilder.setContentText(getString(R.string.file_download_complete));
        notificationManager.notify(0, notificationBuilder.build());
    }

    private void onDownloadFailed() {
        Download download = new Download();
        download.setProgress(0);
        notificationManager.cancel(0);
        notificationBuilder.setProgress(0, 0, false);
        notificationBuilder.setContentText(getString(R.string.error_loadig_file));
        notificationManager.notify(0, notificationBuilder.build());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(0);
    }

}