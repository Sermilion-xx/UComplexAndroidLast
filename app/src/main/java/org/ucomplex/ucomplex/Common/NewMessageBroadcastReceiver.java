package org.ucomplex.ucomplex.Common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import static org.ucomplex.ucomplex.Common.Constants.UC_ACTION_NEW_MESSAGE;
import static org.ucomplex.ucomplex.Modules.Updates.UpdatesService.MESSAGE_COUNT;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 16/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class NewMessageBroadcastReceiver extends BroadcastReceiver {

    private MediaPlayer mAlert;
    private static boolean seen = false;
    private static int messageCount = 0;
    public static void setMessageSeen(boolean isSeen) {
        seen = isSeen;
    }

    public static int getMessageCount() {
        return messageCount;
    }

    public static void subtractMessageCount() {
        messageCount--;
    }

    public NewMessageBroadcastReceiver() {
        mAlert = MediaPlayer.create(UCApplication.getInstance(), R.raw.alert);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int count = intent.getIntExtra(MESSAGE_COUNT, -1);
        if (action.equals(UC_ACTION_NEW_MESSAGE)) {
            if (!seen && messageCount < count) {
                mAlert.start();
                messageCount = count;
                seen = true;
            }
        }
    }

}
