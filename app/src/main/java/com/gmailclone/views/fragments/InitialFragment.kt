package com.gmailclone.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmailclone.utils_classes.AppConstants
import com.gmailclone.views.activities.InitialActivity
import com.gmailclone.R
import com.gmailclone.model.GmailUser
import com.gmailclone.utils_classes.UserListPreference
import com.gmailclone.utils_classes.Utils
import kotlinx.android.synthetic.main.fragment_initial.*
import kotlinx.android.synthetic.main.fragment_initial.emailTextInputEditText
import kotlinx.android.synthetic.main.fragment_initial.emailTextInputLayout

import kotlinx.android.synthetic.main.layout_next_button.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InitialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InitialFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var gmailObject: GmailUser? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_initial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initClicksOfViews();
        super.onViewCreated(view, savedInstanceState)

    }

    fun initClicksOfViews() {
        btn_next?.setOnClickListener(this);
        tv_create_account.setOnClickListener(this)
        tv_forgot_email.setOnClickListener(this)
        emailTextInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Utils.removeErrorWithTextInput(emailTextInputLayout, emailTextInputEditText)
            }
        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InitialFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InitialFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(view: View?) {
        when (view?.id) {


            R.id.btn_next -> {
                if (validation()) {
                    val bundle: Bundle = Bundle();
                    bundle.putSerializable(AppConstants.BUNDLE_KEYS.GMAIL_USER, gmailObject);
                    (activity as InitialActivity).loadNextFragment(102, bundle);
                }
            }
            R.id.tv_forgot_email -> {
                Utils.showToastMessage(
                    activity?.applicationContext,
                    getString(R.string.msg_coming_soon)
                );
            }
            R.id.tv_create_account -> {
                (activity as InitialActivity).loadNextFragment(201, Bundle()); }
        }
    }

    private fun validation(): Boolean {
        if (emailTextInputEditText?.text == null || emailTextInputEditText?.text?.toString()?.trim()
                ?.isEmpty()!!
        ) {
            Utils.showErrorWithTextInput(
                activity,
                emailTextInputLayout,
                R.drawable.ic_error,
                getString(R.string.error_enter_email_phone)
            )
            return false;
        } else {
            val gmailUserList = UserListPreference.getGmailUserList();
            for (gmailUser in gmailUserList) {
                if (gmailUser.username.toString()
                        .equals(emailTextInputEditText.text.toString().trim(), true)
                ) {
                    emailTextInputLayout.error == null
                    emailTextInputEditText.error = null
                    emailTextInputLayout.isErrorEnabled = false
                    gmailObject = gmailUser;
                    return true;
                }

            }
            Utils.showErrorWithTextInput(
                activity,
                emailTextInputLayout,
                R.drawable.ic_error,
                "Couldn't find your Google Account"
            )
            return false;
        }
        return true;
    }
}
