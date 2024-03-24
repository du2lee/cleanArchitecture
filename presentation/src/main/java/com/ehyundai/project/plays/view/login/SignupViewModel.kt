package com.ehyundai.project.plays.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    var mail = MutableLiveData<String>()
    var title: MutableLiveData<String>  = MutableLiveData<String>()

    fun setTitle(flag : Int){
        when (flag){
            1 -> title.value = "메일인증"
            2 -> title.value = "비밀번호 찾기"
            3 -> title.value = "회원가입"
            4 -> title.value = "비밀번호 재설정"
        }
    }

}