package org.ucomplex.ucomplex.Modules.Messenger.model;

import android.net.Uri;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 05/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class MessageFile {

    private final String name;
    private String address;
    private Uri fileUri;
    private boolean downloadaded;
    private final int message;
    private final int from;

    public MessageFile() {
        this.name = "";
        this.address = "";
        this.fileUri = null;
        this.downloadaded = false;
        this.message = -1;
        this.from = -1;
    }

    public MessageFile(String name, Uri fileUri, int from, boolean downloadaded) {
        this.name = name;
        this.address = name;
        this.fileUri = fileUri;
        this.message = -1;
        this.downloadaded = downloadaded;
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMessage() {
        return message;
    }

    public int getFrom() {
        return from;
    }

    public Uri getFileUri() {
        return fileUri;
    }

    public void setFileUri(Uri fileUri) {
        this.fileUri = fileUri;
    }

    public boolean isDownloadaded() {
        return downloadaded;
    }

    public void setDownloadaded(boolean downloadaded) {
        this.downloadaded = downloadaded;
    }
}
