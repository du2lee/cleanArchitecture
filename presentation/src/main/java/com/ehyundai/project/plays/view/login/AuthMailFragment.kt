package com.ehyundai.project.plays.view.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.broooapps.otpedittext2.OnCompleteListener
import com.ehyundai.project.plays.databinding.FragmentAuthMailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthMailFragment : Fragment() {

    private lateinit var binding: FragmentAuthMailBinding
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
        binding = FragmentAuthMailBinding.inflate(layoutInflater)
        binding.vm = viewModel
        initViewModelCallback()
        goSignUp()
        completeCode()

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        signUpActivity = null
        findAccountActivity = null
    }

    private fun goSignUp() {
        binding.btnSignUp.setOnClickListener {
            if (signUpActivity != null && viewModel.checkAuthCode()) {
                signUpActivity?.setFragment(1)
                viewModel.setTitle(3)
            } else if (findAccountActivity != null) {
                findAccountActivity?.setFragment(1)
                viewModel.setTitle(4)
            }
        }
    }

    private fun completeCode() {
        binding.etCode.setOnCompleteListener(OnCompleteListener { value ->  if(signUpActivity != null) viewModel.checkAuthCode(value)
            else if (findAccountActivity != null) Toast.makeText(findAccountActivity, "Completed $value", Toast.LENGTH_LONG).show() }
        )
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goPassword.observe(viewLifecycleOwner) {
                if(signUpActivity != null) {
                    signUpActivity?.setFragment(1)
                    viewModel.setTitle(3)
                }
            }
        }
    }
}