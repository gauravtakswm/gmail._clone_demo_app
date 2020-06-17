package com.gmailclone.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmailclone.utils_classes.AppConstants
import com.gmailclone.views.InitialActivity

import com.gmailclone.R
import com.gmailclone.model.GmailUser
import com.gmailclone.utils_classes.Utils
import kotlinx.android.synthetic.main.fragment_name_input.*
import kotlinx.android.synthetic.main.layout_next_button.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NameInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NameInputFragment : Fragment(),View.OnClickListener {
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
        return inflater.inflate(R.layout.fragment_name_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initClicksOfViews()
        super.onViewCreated(view, savedInstanceState)
    }
    fun initClicksOfViews() {
        btn_next?.setOnClickListener(this);
        /*firstTextInputEditText.setOnFocusChangeListener { view, b ->    Utils.removeErrorWithTextInput(firstTextInputLayout,firstTextInputEditText)
        }
        lastTextInputEditText.setOnFocusChangeListener { view, b ->     Utils.removeErrorWithTextInput(lastTextInputLayout,lastTextInputEditText)
        }*/

        firstTextInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Utils.removeErrorWithTextInput(firstTextInputLayout,firstTextInputEditText)
            }
        })

        lastTextInputEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Utils.removeErrorWithTextInput(lastTextInputLayout,lastTextInputEditText)
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
         * @return A new instance of fragment NameInputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NameInputFragment().apply {
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
                        GmailUser("", "", firstTextInputEditText.text.toString().trim()+" "+lastTextInputEditText.text.toString().trim(), firstTextInputEditText.text.toString().trim(), lastTextInputEditText.text.toString().trim(), "");
                    bundle.putSerializable(AppConstants.BUNDLE_KEYS.GMAIL_USER, gmailUser);
                    (activity as InitialActivity).loadNextFragment(202, bundle);
                }
            }
        }
    }
    private fun validation(): Boolean {
        var isValidInput: Boolean = true;
        if(firstTextInputEditText?.text==null|| firstTextInputEditText?.text?.toString()?.trim()?.isEmpty()!!)
        {
            Utils.showErrorWithTextInput(activity,firstTextInputLayout,R.drawable.ic_error,getString(R.string.error_enter_first_name))

            isValidInput = false;
        }else
        {
            Utils.removeErrorWithTextInput(firstTextInputLayout,firstTextInputEditText);

        }
        if(lastTextInputEditText?.text==null|| lastTextInputEditText?.text?.toString()?.trim()?.isEmpty()!!)
        {
            Utils.showErrorWithTextInput(activity,lastTextInputLayout,R.drawable.ic_error,""+getString(R.string.error_enter_last_name))
            isValidInput = false;

        }else
        {
            Utils.removeErrorWithTextInput(lastTextInputLayout,lastTextInputEditText);

        }
        return isValidInput;
    }
}
