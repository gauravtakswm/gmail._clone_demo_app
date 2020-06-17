package com.gmailclone.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmailclone.utils_classes.AppConstants

import com.gmailclone.R
import com.gmailclone.model.GmailUser
import com.gmailclone.utils_classes.CommonPreferences
import com.gmailclone.utils_classes.NavigationUtils
import com.gmailclone.utils_classes.Utils
import kotlinx.android.synthetic.main.fragment_password_input.*
import kotlinx.android.synthetic.main.fragment_password_input.passwordTextInputEditText
import kotlinx.android.synthetic.main.fragment_password_input.passwordTextInputLayout
import kotlinx.android.synthetic.main.layout_next_button.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var gmailObject: GmailUser? = null;

/**
 * A simple [Fragment] subclass.
 * Use the [PasswordInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PasswordInputFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_password_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initClicksOfViews();
        getBundleArguments();
        super.onViewCreated(view, savedInstanceState)

    }

    private fun getBundleArguments() {
        gmailObject = arguments?.getSerializable(AppConstants.BUNDLE_KEYS.GMAIL_USER) as GmailUser;
tv_email.text = gmailObject?.username;
    }

    fun initClicksOfViews()
    {
        btn_next?.setOnClickListener(this);
        //tv_create_account.setOnClickListener(this)
        tv_forgot_password.setOnClickListener(this)
       /* passwordTextInputEditText.setOnFocusChangeListener { view, b ->      Utils.removeErrorWithTextInput(passwordTextInputLayout,passwordTextInputEditText);

        }*/

        passwordTextInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Utils.removeErrorWithTextInput(passwordTextInputLayout,passwordTextInputEditText)
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
         * @return A new instance of fragment PasswordInputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PasswordInputFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(view: View?) {
        if(view?.id==R.id.btn_next)
        {
            if(validation()) {
                CommonPreferences.setUserData(gmailObject)
                NavigationUtils.launchInboxActivity(activity)
            }
        }else if(view?.id==R.id.tv_forgot_password)
        {
            Utils.showToastMessage(activity?.applicationContext,getString(R.string.msg_coming_soon));

        }
    }

    private fun validation(): Boolean {
        if(passwordTextInputEditText.text.toString().trim().equals(gmailObject?.password)) {
            Utils.removeErrorWithTextInput(passwordTextInputLayout,passwordTextInputEditText);
            return true
        }else
        {
            passwordTextInputEditText.setText("");
            Utils.showErrorWithTextInput(activity,passwordTextInputLayout,R.drawable.ic_error,getString(R.string.error_wrong_password_reset))
        }
        return false;
    }
}
