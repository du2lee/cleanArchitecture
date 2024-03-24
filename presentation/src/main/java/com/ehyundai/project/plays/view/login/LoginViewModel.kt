package com.ehyundai.project.plays.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    val id: MutableLiveData<String> = MutableLiveData("")
    val pw: MutableLiveData<String> = MutableLiveData("")

    private val _isIdEmpty = MutableLiveData<Unit>()
    val isIdEmpty: LiveData<Unit> = _isIdEmpty

    private val _isPwEmpty = MutableLiveData<Unit>()
    val isPwEmpty: LiveData<Unit> = _isPwEmpty

    private val _loginErrorMsg = MutableLiveData<Unit>()
    val loginErrorMsg: LiveData<Unit> = _loginErrorMsg

    private val _successLogin = MutableLiveData<Unit>()
    val successLogin: LiveData<Unit> = _successLogin

    fun onLoginClick() {
        val id = id.value.toString().trim()
        val pw = pw.value.toString().trim()

        if (id.isEmpty()) {
            _isIdEmpty.value = Unit
        } else if (pw.isEmpty()) {
            _isPwEmpty.value = Unit
        } else if (id != USER_ID || pw != USER_PW) {
            _loginErrorMsg.value = Unit
        } else {
            _successLogin.value = Unit
        }
    }

    companion object { //이 아이디와 비번으로만 로그인이 가능 (서버X)
        private const val USER_ID = "id"
        private const val USER_PW = "pass"
    }
}