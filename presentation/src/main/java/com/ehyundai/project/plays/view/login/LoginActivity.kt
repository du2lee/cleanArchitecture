package com.ehyundai.project.plays.view.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ehyundai.project.plays.R
import com.ehyundai.project.plays.databinding.ActivityLoginBinding
import com.ehyundai.project.plays.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        initViewModelCallback()
        login()
        signUp()
        findID()
        findPW()

        context = this
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            isIdEmpty.observe(this@LoginActivity, Observer {
                showIdEmptyError()
            })
            isPwEmpty.observe(this@LoginActivity, Observer {
                showPwEmptyError()
            })
            loginErrorMsg.observe(this@LoginActivity, Observer {
                showToast(getString(R.string.id_pw_not_correct_error_msg))
            })
            successLogin.observe(this@LoginActivity, Observer {
                enterMain()
            })
        }
    }

    private fun showIdEmptyError() {
        binding.etEmail.error = getString(R.string.id_empty_error_msg)
    }

    private fun showPwEmptyError() {
        binding.etPassword.error = getString(R.string.pw_empty_error_msg)
    }

    private fun enterMain() {
        showToast(getString(R.string.login_success_msg))
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun findAccount(type: String) {
        val signUpIntent = Intent(this, FindAccountActivity::class.java)
        when (type) {
            "ID" -> signUpIntent.putExtra("type", "ID")
            else -> signUpIntent.putExtra("type", "PW")
        }
        startActivity(signUpIntent)
    }

    private fun enterSignUp() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    private fun login() {
        binding.btnSignIn.setOnClickListener { viewModel.onLoginClick(context) }
    }

    private fun signUp() {
        binding.btnSignUp.setOnClickListener { enterSignUp() }
    }

    private fun findID() {
        binding.btnFindId.setOnClickListener {
            val url = "https://webmail.ehyundai.com/mail/find/id"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun findPW() {
        binding.btnFindPw.setOnClickListener {
            val signUpIntent = Intent(this, FindAccountActivity::class.java)
            startActivity(signUpIntent)
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}