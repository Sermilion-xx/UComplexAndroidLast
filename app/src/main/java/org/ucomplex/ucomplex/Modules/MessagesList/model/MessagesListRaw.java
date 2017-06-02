package org.ucomplex.ucomplex.Modules.MessagesList.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class MessagesListRaw {

    private final List<MessagesListItem> dialogs;

    public MessagesListRaw() {
        this.dialogs = new ArrayList<>();
    }

    public List<MessagesListItem> getDialogs() {
        return dialogs;
    }
}
