package com.gmailclone.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import com.gmailclone.R
import com.gmailclone.utils_classes.CommonPreferences
import com.gmailclone.utils_classes.NavigationUtils
import kotlinx.android.synthetic.main.activity_inbox.*

class InboxActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox)
        initViews()
    }

    private fun initViews() {
        btn_logout.setOnClickListener(this)
        val gmailUser = CommonPreferences.getUserData();
        tv_name_greetings.text =
            Html.fromHtml("Hi <b>${gmailUser.full_name}</b>! <br>Have a nice beautiful day.")
        tv_username.text = Html.fromHtml("Your username is <b>${gmailUser.username}</b>")

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_logout -> {
                CommonPreferences.clearPreferences()
                NavigationUtils.launchInitialActivity(this)
            }

        }
    }
}
