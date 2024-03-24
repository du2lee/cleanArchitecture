package com.ehyundai.project.plays.view.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ehyundai.project.plays.R
import com.ehyundai.project.plays.databinding.ActivityFindAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFindAccountBinding
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.vm = viewModel
        viewModel.setTitle(2)
        setFragment(3)

        context = this
    }

    @SuppressLint("CommitTransaction")
    fun setFragment(type: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when(type){
            1 -> transaction.replace(R.id.fl, PasswordFragment()).commit()
            2 -> transaction.replace(R.id.fl, AuthMailFragment()).commit()
            else -> transaction.replace(R.id.fl, MailFragment()).commit()
        }
    }
}