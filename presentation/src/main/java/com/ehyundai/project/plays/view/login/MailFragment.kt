package com.ehyundai.project.plays.view.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ehyundai.project.plays.databinding.FragmentMailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MailFragment : Fragment() {
    private lateinit var binding: FragmentMailBinding
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

        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        binding = FragmentMailBinding.inflate(layoutInflater)
        binding.vm = viewModel
        goAuthMail()

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        signUpActivity = null
        findAccountActivity = null
    }

    private fun goAuthMail() {
        binding.btnSignUp.setOnClickListener {
            if (signUpActivity != null) {
                viewModel.getAuth()
                signUpActivity?.setFragment(2)
            }
            else if (findAccountActivity != null) findAccountActivity?.setFragment(2)
        }
    }
}