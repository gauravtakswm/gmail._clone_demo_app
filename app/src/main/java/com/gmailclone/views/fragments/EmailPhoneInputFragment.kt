package com.gmailclone.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmailclone.views.activities.InitialActivity

import com.gmailclone.R
import com.gmailclone.model.GmailUser
import com.gmailclone.utils_classes.UserListPreference
import com.gmailclone.utils_classes.Utils
import kotlinx.android.synthetic.main.fragment_email_phone_input.emailTextInputEditText
import kotlinx.android.synthetic.main.fragment_email_phone_input.emailTextInputLayout
import kotlinx.android.synthetic.main.layout_next_button.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EmailPhoneInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmailPhoneInputFragment : Fragment(), View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_email_phone_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initClicksOfViews()
        super.onViewCreated(view, savedInstanceState)
    }

    fun initClicksOfViews() {
        btn_next?.setOnClickListener(this);

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
         * @return A new instance of fragment EmailPhoneInputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EmailPhoneInputFragment().apply {
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
                    val gmailUser =
                        GmailUser(emailTextInputEditText.text.toString(), "", "", "", "", "");
                    bundle.putSerializable("gmail_user", gmailUser);
                    (activity as InitialActivity).loadNextFragment(102, bundle);
                }
            }
        }
    }

    private fun validation(): Boolean {
        if (emailTextInputEditText?.text == null || emailTextInputEditText?.text?.isEmpty()!!) {
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
                        .equals(emailTextInputEditText.text.toString(), true)
                ) {
                    Utils.showErrorWithTextInput(
                        activity,
                        emailTextInputLayout,
                        R.drawable.ic_error,
                        getString(R.string.error_already_taken)
                    )
                    return false;
                }

            }
            Utils.removeErrorWithTextInput(emailTextInputLayout, emailTextInputEditText);
            return true;
        }
        return true;
    }
}

