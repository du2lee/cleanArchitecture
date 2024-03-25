package com.ehyundai.project.plays.view.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ehyundai.project.plays.databinding.FragmentPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordFragment : Fragment() {

    private lateinit var binding: FragmentPasswordBinding
    private lateinit var context: Context
    private val viewModel by activityViewModels<SignUpViewModel>()

    private var signUpActivity: SignUpActivity? = null
    private var findAccountActivity: FindAccountActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SignUpActivity)
            signUpActivity = context
        else if (context is FindAccountActivity)
            findAccountActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentPasswordBinding.inflate(layoutInflater)
        binding.vm = viewModel
        setButtonName()
        clickButton()

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        signUpActivity = null
        findAccountActivity = null
    }

    private fun clickButton() {

        binding.btnSignUp.setOnClickListener {
            if (signUpActivity != null){
                if(viewModel.verifyPwd())
                    signUpActivity?.setFragment(4)
                else
                    Toast.makeText(signUpActivity, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
            }
            else if (findAccountActivity != null) findAccountActivity?.finish()
        }
    }

    private fun setButtonName(){
        if (findAccountActivity != null) binding.btnSignUp.text = "로그인 하러가기"
    }
}