package com.ehyundai.project.plays.view.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ehyundai.project.plays.R
import com.ehyundai.project.plays.databinding.FragmentClubHomeBinding
import com.ehyundai.project.plays.databinding.FragmentNicknameBinding
import com.ehyundai.project.plays.view.club.ClubActivity
import com.ehyundai.project.plays.view.club.ClubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NicknameFragment : Fragment() {

    private lateinit var binding: FragmentNicknameBinding
    private lateinit var context: Context
    private val viewModel by activityViewModels<SignUpViewModel>()

    private var parentActivity: Activity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as SignUpActivity
        parentActivity = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNicknameBinding.inflate(layoutInflater)
        binding.vm = viewModel
        finishSignUp()

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        parentActivity = null
    }

    private fun finishSignUp(){
        binding.btnSignUp.setOnClickListener {
            if(parentActivity != null) parentActivity?.finish() }
    }
}