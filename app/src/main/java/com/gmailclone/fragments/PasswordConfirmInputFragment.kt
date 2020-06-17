package com.gmailclone.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gmailclone.R
import com.gmailclone.model.GmailUser
import com.gmailclone.utils_classes.*
import kotlinx.android.synthetic.main.fragment_password_confirm_input.*
import kotlinx.android.synthetic.main.fragment_password_confirm_input.passwordTextInputEditText
import kotlinx.android.synthetic.main.fragment_password_confirm_input.passwordTextInputLayout
import kotlinx.android.synthetic.main.fragment_password_input.*
import kotlinx.android.synthetic.main.layout_next_button.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PasswordConfirmInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PasswordConfirmInputFragment : Fragment(),View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_password_confirm_input, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initClicksOfViews()
        getBundleArguments()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun getBundleArguments() {
        gmailObject = arguments?.getSerializable(AppConstants.BUNDLE_KEYS.GMAIL_USER) as GmailUser;

    }
    fun initClicksOfViews() {
        btn_next?.setOnClickListener(this);


        passwordTextInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Utils.removeErrorWithTextInput(passwordTextInputLayout,passwordTextInputEditText)
            }
        })

        confirmPasswordTextInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Utils.removeErrorWithTextInput(confirmPasswordTextInputLayout,confirmPasswordTextInputEditText) }
        })
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PasswordConfirmInputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PasswordConfirmInputFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(view: View?) {
        when(view?.id)
        {
            R.id.btn_next->{
                if(validation())
                {

                     gmailObject?.password = passwordTextInputEditText.text.toString().trim()
                    gmailObject?.gmail_social_id = Utils.getCurrentTimeStamp();
                    val arrayListOfUsers = UserListPreference.getGmailUserList();
                    arrayListOfUsers.add(gmailObject);
                    UserListPreference.setGmailUserList(arrayListOfUsers);
                    CommonPreferences.setUserData(gmailObject)
                    NavigationUtils.launchInboxActivity(activity)



                }
            }
        }


        {

        }
    }

    private fun validation(): Boolean {
        var isValidInput1: Boolean = true;
        var isValidInput2: Boolean = true;

        if(passwordTextInputEditText?.text==null|| passwordTextInputEditText?.text?.toString()?.trim()?.isEmpty()!!)
        {
            Utils.showErrorWithTextInput(activity,passwordTextInputLayout,R.drawable.ic_error,getString(R.string.error_enter_password))
            isValidInput1 = false;
        }else if(!Utils.isValidPassword(passwordTextInputEditText?.text.toString().trim()))
        {
            Utils.showErrorWithTextInput(activity,passwordTextInputLayout,R.drawable.ic_error,getString(R.string.error_user_strong_password))
            isValidInput1 = false;

        }
        else
        {
            Utils.removeErrorWithTextInput(passwordTextInputLayout,passwordTextInputEditText);

        }
        if(confirmPasswordTextInputEditText?.text==null|| confirmPasswordTextInputEditText?.text?.toString()?.trim()?.isEmpty()!!)
        {
            Utils.showErrorWithTextInput(activity,confirmPasswordTextInputLayout,R.drawable.ic_error,getString(R.string.error_enter_confirm_password))
            isValidInput2 = false;

        }else if(!passwordTextInputEditText?.text.toString().trim().equals(confirmPasswordTextInputEditText?.text.toString().trim()))
        {
            Utils.showErrorWithTextInput(activity,confirmPasswordTextInputLayout,R.drawable.ic_error,getString(R.string.error_password_confirm_not_match))
            isValidInput2 = false;

        }else
        {
            Utils.removeErrorWithTextInput(confirmPasswordTextInputLayout,confirmPasswordTextInputEditText);

        }

        return (isValidInput1 && isValidInput2);
    }
}
