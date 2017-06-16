package org.ucomplex.ucomplex.Modules.Updates;

import java.util.Map;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 16/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class UpdatesRaw {

    private final Messages messages;
    private final boolean notifications;
    private final boolean friends_req;

    public UpdatesRaw() {
        this.messages = null;
        this.notifications = false;
        this.friends_req = false;
    }

    public Messages getMessages() {
        return messages;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public boolean isFriends_req() {
        return friends_req;
    }

    public class Messages {
        private final int sum;

        public Messages(int sum) {
            this.sum = sum;
        }

        public int getSum() {
            return sum;
        }
    }
}
