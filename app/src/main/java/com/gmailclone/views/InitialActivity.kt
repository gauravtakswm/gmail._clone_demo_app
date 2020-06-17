package com.gmailclone.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gmailclone.R
import com.gmailclone.fragments.*
import com.gmailclone.utils_classes.CommonPreferences
import com.gmailclone.utils_classes.NavigationUtils
import kotlinx.android.synthetic.main.activity_initial.*

class InitialActivity : AppCompatActivity() {
    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
        if(CommonPreferences.getUserData()==null)
             loadNextFragment(101,Bundle());
        else
            NavigationUtils.launchInboxActivity(this)
    }

    public fun loadNextFragment(fragmentSeq:Int,bundle:Bundle) {
        var signInLabelValue :String? = null;
        var fragmentClass: Class<*>? = null
         fragmentClass = when (fragmentSeq) {
             101 -> InitialFragment::class.java
             102 -> {
                 signInLabelValue = getString(R.string.label_welcome)
                 PasswordInputFragment::class.java
             }
           //  2 -> EmailPhoneInputFragment::class.java
             201 -> {
                 signInLabelValue = getString(R.string.label_create_a_google_account)
                 NameInputFragment::class.java}
             202 -> {
                 signInLabelValue = getString(R.string.label_how_you_will_signin)
                 UsernameInputFragment::class.java
             }
             203 -> {
                 signInLabelValue = getString(R.string.label_create_strong_password)
                 PasswordConfirmInputFragment::class.java
             }
             204 -> EmailPhoneInputFragment::class.java

             else -> InitialFragment::class.java
         }
        if(signInLabelValue!=null)
        tv_signin.text = signInLabelValue;
        try {
            fragment = fragmentClass?.newInstance() as Fragment
            fragment?.arguments = bundle;
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Insert the fragment by replacing any existing fragment
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment!!).addToBackStack(fragment?.javaClass.toString()).commit()

    }

    override fun onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            getSupportFragmentManager().popBackStack();
        else
            finish();
    }
}
