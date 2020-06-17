package com.gmailclone;

import android.app.Application;
import android.content.Context;

import com.gmailclone.model.GmailUser;
import com.gmailclone.utils_classes.UserListPreference;
import com.gmailclone.utils_classes.Utils;

import java.util.ArrayList;

public class GmailCloneApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = this;
        getDefaultUserList();
        super.onCreate();
    }

    private void getDefaultUserList() {
        ArrayList<GmailUser> gmailUserArrayList = UserListPreference.getGmailUserList();
        if (gmailUserArrayList == null) {
            //reading the json data at initial stage
            gmailUserArrayList = Utils.getAssetJsonDataOfGmails(context);
            UserListPreference.setGmailUserList(gmailUserArrayList); // saving the data into shared-preference
        }
    }

    public static Context getContext() {
        return context;
    }

}
