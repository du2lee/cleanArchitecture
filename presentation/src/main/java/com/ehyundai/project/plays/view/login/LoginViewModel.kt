package com.ehyundai.project.plays.view.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehyundai.project.domain.usecase.GetMemberUseCase
import com.ehyundai.project.plays.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getMemberUseCase: GetMemberUseCase
): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

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

    fun onLoginClick(context: Context) {
        val id = id.value.toString().trim()
        val pw = pw.value.toString().trim()

        if (id.isEmpty()) {
            _isIdEmpty.value = Unit
            return
        } else if (pw.isEmpty()) {
            _isPwEmpty.value = Unit
            return
        }

        compositeDisposable.add(
            getMemberUseCase.login(id, pw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError{
                        e -> Log.e("onError()", e.message.toString())
                    Log.e("onError()", e.cause.toString())
                    _loginErrorMsg.value = Unit
                }
                .subscribe { response ->
                    Util.setPreference(context, "accessToken", response.accessToken)
                    Util.setPreference(context, "refreshToken", response.refreshToken)
                    _successLogin.value = Unit
                })
    }
}