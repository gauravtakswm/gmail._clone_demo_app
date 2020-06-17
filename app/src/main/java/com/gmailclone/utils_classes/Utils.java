package com.gmailclone.utils_classes;

import android.app.Activity;
import android.app.Person;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.Toast;

import com.gmailclone.R;
import com.gmailclone.model.GmailUser;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static void showErrorWithTextInput(Activity activity,TextInputLayout textInputLayout, int icon, String errorMsg)
    {
        textInputLayout.setErrorEnabled(true);
        if(icon!=0) {
            textInputLayout.setErrorIconDrawable(null);
            ImageSpan imageSpan = new ImageSpan(activity, icon);
            SpannableString spannableString = new SpannableString("   " + errorMsg);

            int start = 0;
            int end = 1;
            int flag = 0;
            spannableString.setSpan(imageSpan, 0, 1, flag);
            textInputLayout.setError(spannableString);
        }else
        {
            textInputLayout.setError(errorMsg);
        }
    }
    public static void removeErrorWithTextInput(TextInputLayout textInputLayout, TextInputEditText textInputEditText)
    {
        textInputLayout.setError(null);
        textInputEditText.setError(null);
        textInputLayout.setErrorEnabled(false);

    }

    public static ArrayList<GmailUser> getAssetJsonDataOfGmails(Context context) {


        String json = null;
        try {
            InputStream is = context.getAssets().open("user_details.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
     Type type = new TypeToken<ArrayList<GmailUser>>() {
        }.getType();
        ArrayList<GmailUser> personArrayList = new Gson().fromJson(json, type);
        return personArrayList;
    }
    public static void showToastMessage(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
    public static String getCurrentTimeStamp()
    {
        Long tsLong = System.currentTimeMillis();
        String ts = tsLong.toString();
        return ts;
    }

}
