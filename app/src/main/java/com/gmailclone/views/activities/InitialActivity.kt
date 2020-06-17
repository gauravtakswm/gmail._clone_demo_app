package com.gmailclone.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gmailclone.R
import com.gmailclone.views.fragments.*
import com.gmailclone.utils_classes.CommonPreferences
import com.gmailclone.utils_classes.NavigationUtils
import kotlinx.android.synthetic.main.activity_initial.*

class InitialActivity : AppCompatActivity() {
    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
        //if user already logged in then launch Inbox Activity else launch new registration/login flow
        if (CommonPreferences.getUserData() == null)
            loadNextFragment(101, Bundle());
        else
            NavigationUtils.launchInboxActivity(this)
    }

    public fun loadNextFragment(fragmentSeq: Int, bundle: Bundle) {
        var signInLabelValue: String? = null;
        var fragmentClass: Class<*>? = null
        fragmentClass = when (fragmentSeq) {
            //101,102 represents the login flow for existing users
            101 -> InitialFragment::class.java
            102 -> {
                signInLabelValue = getString(R.string.label_welcome)
                PasswordInputFragment::class.java
            }
            //201,202,203,204 represents the new account creation flow for new users
            201 -> {
                signInLabelValue = getString(R.string.label_create_a_google_account)
                NameInputFragment::class.java
            }
            202 -> {
                signInLabelValue = getString(R.string.label_how_you_will_signin)
                UsernameInputFragment::class.java
            }
            203 -> {
                signInLabelValue = getString(R.string.label_create_strong_password)
                PasswordConfirmInputFragment::class.java
            }
            //there is no need of this
            204 -> EmailPhoneInputFragment::class.java

            else -> InitialFragment::class.java
        }
        if (signInLabelValue != null)
            tv_signin.text = signInLabelValue;
        try {
            fragment = fragmentClass?.newInstance() as Fragment
            fragment?.arguments = bundle;
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Insert the fragment by replacing any existing fragment
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment!!)
            .addToBackStack(fragment?.javaClass.toString()).commit()

    }


    override fun onBackPressed() {
        //doing pop back Stack based of fragments stacks
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            getSupportFragmentManager().popBackStack();
        else
            finish();
    }
}
