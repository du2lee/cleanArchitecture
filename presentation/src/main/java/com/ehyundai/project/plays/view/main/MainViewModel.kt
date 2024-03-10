package com.ehyundai.project.plays.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.domain.usecase.GetClubsUseCase
import com.ehyundai.project.domain.usecase.TestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getClubsUseCase: GetClubsUseCase,
    private val testUseCase: TestUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _clubList = MutableLiveData<ArrayList<Club>>()
    val clubList: LiveData<ArrayList<Club>> get() = _clubList

    fun getClub(){
        compositeDisposable.add(
            getClubsUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    // 로딩바 노출
                }
                .doAfterTerminate {
                    // 로딩바 숨기기
                }
                .subscribe({ clubs ->
                    if (clubs.isEmpty()) {
                        // 값이 제로
                    } else {
                        // 성공
                        Log.i("duhui", "success")
                        _clubList.value = clubs as ArrayList<Club>
                        Log.i("duhui", _clubList.value.toString())
                    }
                }, {
                    // 에러
                })
        )
    }

    init {
        Log.i("duhui", "start")
        getClub()
        Log.i("duhui", "end")
    }

    fun testFunc(){
        compositeDisposable.add(
            testUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    // 로딩바 노출
                }
                .doAfterTerminate {
                    // 로딩바 숨기기
                }
                .subscribe({ clubs ->
                    if (clubs.isEmpty()) {
                        // 값이 제로
                    } else {
                        // 성공
                        Log.i("duhui", "success")
                        _clubList.value = clubs as ArrayList<Club>
                        Log.i("duhui", _clubList.value.toString())
                    }
                }, {
                    // 에러
                })
        )
    }


}



