package com.ehyundai.project.plays.view.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ehyundai.project.plays.R
import com.ehyundai.project.plays.databinding.ActivitySignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel.setTitle(1)
        setFragment(3)
    }

    @SuppressLint("CommitTransaction")
    fun setFragment(type: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when (type) {
            1 -> transaction.replace(R.id.fl, PasswordFragment()).commit()
            2 -> transaction.replace(R.id.fl, AuthMailFragment()).commit()
            3 -> transaction.replace(R.id.fl, MailFragment()).commit()
            4 -> transaction.replace(R.id.fl, NicknameFragment()).commit()
        }
    }
}