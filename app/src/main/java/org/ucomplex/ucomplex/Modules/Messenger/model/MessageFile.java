package org.ucomplex.ucomplex.Modules.Messenger.model;

import android.net.Uri;

import java.util.List;

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
    private final String address;
    private final Uri fileUri;
    private boolean downloaded;
    private final int message;
    private final int from;

    public MessageFile() {
        this.name = "";
        this.address = "";
        this.fileUri = null;
        this.downloaded = false;
        this.message = -1;
        this.from = -1;
    }

    public MessageFile(String name, Uri fileUri, int from, boolean downloaded) {
        this.name = name;
        this.address = name;
        this.fileUri = fileUri;
        this.message = -1;
        this.downloaded = downloaded;
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
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

    public boolean isDownloaded() {
        return downloaded;
    }

    public void setDownloaded(boolean downloaded) {
        this.downloaded = downloaded;
    }
}
