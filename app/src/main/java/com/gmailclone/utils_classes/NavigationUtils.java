package com.gmailclone.utils_classes;

import android.app.Activity;
import android.content.Intent;

import com.gmailclone.GmailCloneApplication;
import com.gmailclone.views.InboxActivity;
import com.gmailclone.views.InitialActivity;

public class NavigationUtils {
    public static void launchInboxActivity(Activity activity)
    {
        Intent intent = new Intent(GmailCloneApplication.getContext(), InboxActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
    public static void launchInitialActivity(Activity activity)
    {
        Intent intent = new Intent(GmailCloneApplication.getContext(), InitialActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
}
