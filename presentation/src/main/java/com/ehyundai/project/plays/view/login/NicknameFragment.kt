package com.ehyundai.project.plays.view.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
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
        initViewModelCallback()
        clickSignUp()

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        parentActivity = null
    }

    private fun clickSignUp(){
        binding.btnSignUp.setOnClickListener { viewModel.checkNickName() }
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            checkNickname.observe(viewLifecycleOwner) {
                viewModel.signUp()
            }

            failNickname.observe(viewLifecycleOwner) {
                Log.i("duhui1", "실패")
                Toast.makeText(context, "중복된 닉네임 입니다.", Toast.LENGTH_LONG).show()
            }

            signup.observe(viewLifecycleOwner) {
                Log.i("duhui2", "성공")
                Toast.makeText(context, "회원가입 되었습니다.", Toast.LENGTH_LONG).show()
                if(parentActivity != null) parentActivity?.finish()
            }

            failSignup.observe(viewLifecycleOwner) {
                Log.i("duhui3", "실패")
                Toast.makeText(context, "에러가 발생했습니다..", Toast.LENGTH_LONG).show()
            }
        }
    }
}