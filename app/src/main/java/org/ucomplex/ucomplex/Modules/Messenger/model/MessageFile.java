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
    private final int message;
    private final int from;

    public MessageFile() {
        this.name = "";
        this.address = "";
        this.fileUri = null;
        this.message = -1;
        this.from = -1;
    }

    public MessageFile(String name, Uri fileUri, int from) {
        this.name = name;
        this.address = name;
        this.fileUri = fileUri;
        this.message = -1;
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
}
