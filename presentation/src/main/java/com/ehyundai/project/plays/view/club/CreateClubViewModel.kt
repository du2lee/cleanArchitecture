package com.ehyundai.project.plays.view.club

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehyundai.project.domain.model.Company
import com.ehyundai.project.domain.usecase.GetCompanyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CreateClubViewModel @Inject constructor(
    private  val getCompanyUseCase: GetCompanyUseCase): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _companyList = MutableLiveData<ArrayList<Company>>()
    val companyList: LiveData<ArrayList<Company>> get() = _companyList

    init {
        getCompany()
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

    fun getCompanyList(): ArrayList<String>? {
        var companys: ArrayList<String> = arrayListOf()
        for (company in companyList.value!!){
            companys.add(company.name)
        }
        return companys
    }
}