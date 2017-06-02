package org.ucomplex.ucomplex.Modules.MessagesList.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class MessagesListItem {

    private final int companion;
    private final int from;
    private final String message;
    private final String time;
    private final int status;
    private final String name;
    private final String code;
    private final int photo;

    public MessagesListItem() {
        this.companion = 0;
        this.from = 0;
        this.message = "";
        this.time = "";
        this.status = 0;
        this.name = "";
        this.code = "";
        this.photo = 0;
    }

    public int getCompanion() {
        return companion;
    }

    public int getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getPhoto() {
        return photo;
    }
}
