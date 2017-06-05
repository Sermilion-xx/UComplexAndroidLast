package org.ucomplex.ucomplex.Modules.Messenger.model;

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
    private final int message;
    private final int from;

    public MessageFile() {
        this.name = "";
        this.address = "";
        this.message = 0;
        this.from = 0;
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
}
