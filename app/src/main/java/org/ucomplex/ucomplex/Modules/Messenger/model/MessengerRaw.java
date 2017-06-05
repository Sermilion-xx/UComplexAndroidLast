package org.ucomplex.ucomplex.Modules.Messenger.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class MessengerRaw {

    private final List<MessengerItem> messages;
    private final CompanionInfo companion_info;
    private final Map<Integer, List<MessageFile>> files;

    public MessengerRaw() {
        this.messages = new ArrayList<>();
        this.companion_info = new CompanionInfo();
        this.files = new HashMap<>();
    }

    public List<MessengerItem> getMessages() {
        return messages;
    }

    public CompanionInfo getCompanion_info() {
        return companion_info;
    }

    public Map<Integer, List<MessageFile>> getFiles() {
        return files;
    }
}
