package com.gmailclone.utils_classes;

import android.content.Context;
import android.content.SharedPreferences;
import com.gmailclone.GmailCloneApplication;
import com.gmailclone.model.GmailUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserListPreference {

    public static final String PREFRENCE_STORE_KEY = "user_list_prefs";
    public static void setPreferences(String key, String value) {
        Context con = GmailCloneApplication.getContext();
        // save the data
        SharedPreferences preferences = con.getSharedPreferences(PREFRENCE_STORE_KEY, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static ArrayList<GmailUser> getGmailUserList()
    {
        Type type = new TypeToken<ArrayList<GmailUser>>() {
        }.getType();
        try{ArrayList<GmailUser> gmailUserArrayList = new Gson().fromJson(UserListPreference.getPreferences(AppConstants.SHARED_PREF_KEYS.GMAIL_USER_LIST).toString(), type);
            return gmailUserArrayList;}
        catch (Exception e){
            return null;
        }
    }
    public static void setGmailUserList(ArrayList<GmailUser> gmailUserList)
    {
        String list = new Gson().toJson(gmailUserList);
        UserListPreference.setPreferences(AppConstants.SHARED_PREF_KEYS.GMAIL_USER_LIST,list);

    }
    public static String getPreferences(String key) {
        Context con = GmailCloneApplication.getContext();
        SharedPreferences sharedPreferences = con.getSharedPreferences(PREFRENCE_STORE_KEY, 0);
        String value = sharedPreferences.getString(key, null);
        return value;

    }




    public static void clearPreferences() {
        Context con = GmailCloneApplication.getContext();
        con.getSharedPreferences(PREFRENCE_STORE_KEY, 0).edit().clear().commit();

    }





}
