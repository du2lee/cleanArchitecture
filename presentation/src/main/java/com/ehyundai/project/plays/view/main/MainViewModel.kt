package com.ehyundai.project.plays.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.domain.model.Company
import com.ehyundai.project.domain.usecase.GetClubsUseCase
import com.ehyundai.project.domain.usecase.GetCompanyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getClubsUseCase: GetClubsUseCase,
    private val getCompanyUseCase: GetCompanyUseCase
) : ViewModel() {

    private val _initCompany = MutableLiveData<Unit>()
    val initCompany: LiveData<Unit> = _initCompany

    private val compositeDisposable = CompositeDisposable()

    private val _clubList = MutableLiveData<ArrayList<Club>>()
    val clubList: LiveData<ArrayList<Club>> get() = _clubList

    private val _companyList = MutableLiveData<ArrayList<Company>>()
    private val companyList: LiveData<ArrayList<Company>> get() = _companyList

    init {
        getCompany()
        getClub()
    }

    private fun getClub(){
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
                        _clubList.value = clubs as ArrayList<Club>
                    }
                }, {
                    // 에러
                })
        )
    }

    private fun getCompany(){
        compositeDisposable.add(
            getCompanyUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {}
                .doAfterTerminate {}
                .subscribe({ companys ->
                    if (companys.isEmpty()) {
                        // 값이 제로
                    } else {
                        _companyList.value = companys as ArrayList<Company>
                        _initCompany.value = Unit
                    }
                }, {
                    // 에러
                })
        )
    }

    fun getCompanyList(): ArrayList<Company>? {
        return companyList.value
    }

    fun getClubList(): ArrayList<Club>? {
        return clubList.value
    }

    fun clickCompany(companyNo: Int, company: String){
        compositeDisposable.add(
            getClubsUseCase.clickCompany(companyNo, company)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    // 로딩바 노출
                }
                .doAfterTerminate {
                    // 로딩바 숨기기
                }
                .subscribe { clubs ->
                    if (clubs.isEmpty()) {
                        // 값이 제로
                    } else {
                        // 성공
                        _clubList.value = clubs as ArrayList<Club>
                    }
                }
        )
    }
}



