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
    private val compositeDisposable = CompositeDisposable()

    private val _clubList = MutableLiveData<ArrayList<Club>>()
    val clubList: LiveData<ArrayList<Club>> get() = _clubList

    private val _companyList = MutableLiveData<ArrayList<Company>>()
    val companyList: LiveData<ArrayList<Company>> get() = _companyList

    init {
        init()
    }

    fun init(){
        getCompany()
        getClub()
    }

    private fun getClub(){
        compositeDisposable.add(
            getClubsUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ clubs ->
                    if (clubs.isEmpty()) {}
                    else { _clubList.value = clubs as ArrayList<Club> }
                }, { })
        )
    }

    private fun getCompany(){
        compositeDisposable.add(
            getCompanyUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ companys ->
                    if (companys.isEmpty()) {}
                    else {_companyList.value = companys as ArrayList<Company>}
                }, { })
        )
    }

    fun clickCompany(company: String){
        compositeDisposable.add(
            getClubsUseCase.clickCompany(company)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { clubs ->
                    if (clubs.isEmpty()) {}
                    else {_clubList.value = clubs as ArrayList<Club> }
                }
        )
    }

    fun getCompanyList(): ArrayList<Company>? {
        return companyList.value
    }

    fun getClubList(): ArrayList<Club>? {
        return clubList.value
    }
}



