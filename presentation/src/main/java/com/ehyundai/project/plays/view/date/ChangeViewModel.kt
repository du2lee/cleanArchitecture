package com.ehyundai.project.plays.view.date

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ChangeViewModel @Inject constructor() : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val pw: MutableLiveData<String> = MutableLiveData("")
    val verifyPw: MutableLiveData<String> = MutableLiveData("")
    val nickname: MutableLiveData<String> = MutableLiveData("")
    val companyNo: MutableLiveData<String> = MutableLiveData("")

    var mail = MutableLiveData<String>()
    var authNum = MutableLiveData<String>()
    var title: MutableLiveData<String>  = MutableLiveData<String>()
    var subTitle: MutableLiveData<String>  = MutableLiveData<String>()

    fun setTitle(flag : Int){
        when (flag){
            0 -> {
                title.value = "비밀번호 변경"
                subTitle.value = "비밀번호"
            }
            1 -> {
                title.value = "닉네임 변경"
                subTitle.value = "닉네임"
            }
            2 -> {
                title.value = "소속회사 변경"
                subTitle.value = "소속회사"
            }
            3 -> {
                title.value = "부서 변경"
                subTitle.value = "부서"
            }
            4 -> {
                title.value = "활동지역 변경"
                subTitle.value = "활동지역"
            }
            5 -> {
                title.value = "자기소개 변경"
                subTitle.value = "자기소개"
            }
        }
    }
}

