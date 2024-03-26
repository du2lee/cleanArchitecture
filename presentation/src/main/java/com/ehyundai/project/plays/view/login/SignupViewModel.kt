package com.ehyundai.project.plays.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehyundai.project.domain.model.AuthCode
import com.ehyundai.project.domain.usecase.GetMemberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val getMemberUseCase: GetMemberUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val pw: MutableLiveData<String> = MutableLiveData("")
    val verifyPw: MutableLiveData<String> = MutableLiveData("")
    val nickname: MutableLiveData<String> = MutableLiveData("")

    var mail = MutableLiveData<String>()
    var authNum = MutableLiveData<String>()
    var title: MutableLiveData<String>  = MutableLiveData<String>()

    private val _authCode = MutableLiveData<AuthCode>()
    val authCode: LiveData<AuthCode> get() = _authCode

    private val _goPassword = MutableLiveData<Unit>()
    val goPassword: LiveData<Unit> = _goPassword

    private val _checkNickname = MutableLiveData<Unit>()
    val checkNickname: LiveData<Unit> = _checkNickname

    private val _failNickname = MutableLiveData<Unit>()
    val failNickname: LiveData<Unit> = _failNickname

    private val _signup = MutableLiveData<Unit>()
    val signup: LiveData<Unit> = _signup

    private val _failSignup = MutableLiveData<Unit>()
    val failSignup: LiveData<Unit> = _failSignup

    fun setTitle(flag : Int){
        when (flag){
            1 -> title.value = "메일인증"
            2 -> title.value = "비밀번호 찾기"
            3 -> title.value = "회원가입"
            4 -> title.value = "비밀번호 재설정"
        }
    }

    // send authCode to email
    fun getAuth(){
        compositeDisposable.add(
            getMemberUseCase.getAuthCode(mail.value.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { authCode -> _authCode.value = authCode }
        )
    }

    fun checkAuthCode(): Boolean{
        return authNum.value == authCode.value!!.authCode
    }

    fun checkAuthCode(num: String){
        compositeDisposable.add(
            getMemberUseCase.verifyAuthCode(mail.value.toString(), num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    if (response.authCode == "success") { _goPassword.value = Unit } })
        authNum.value = num
    }

    fun verifyPwd(): Boolean {
        return verifyPw.value == pw.value
    }

    fun checkNickName(){
        compositeDisposable.add(
            getMemberUseCase.checkDuplicatedNickname(nickname.value.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    if (response.authCode == "success") { _checkNickname.value = Unit }
                    else{ _failNickname.value = Unit } })
    }

    fun signUp(){
        compositeDisposable.add(
            getMemberUseCase.signUp(mail.value.toString(), pw.value.toString(), nickname.value.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError{
                    e -> Log.e("onError()", e.message.toString())
                    Log.e("onError()", e.cause.toString())
                    _failSignup.value = Unit
                }
                .subscribe { response ->
                    if (response.authCode == "success") { _signup.value = Unit }
                    else { _failSignup.value = Unit } })
    }

}

