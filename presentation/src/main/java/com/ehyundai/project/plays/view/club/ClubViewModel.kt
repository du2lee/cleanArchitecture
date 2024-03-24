package com.ehyundai.project.plays.view.club

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehyundai.project.domain.model.ClubInfo
import com.ehyundai.project.domain.usecase.GetClubsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ClubViewModel @Inject constructor(
    private val getClubsUseCase: GetClubsUseCase
): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _clubInfo = MutableLiveData<ClubInfo>()
    val clubInfo: LiveData<ClubInfo> get() = _clubInfo

    fun getClubInfo(clubNo: Int){
        compositeDisposable.add(
            getClubsUseCase.clickClub(clubNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ club -> _clubInfo.value = club
                    Log.i("duhui", clubInfo.value!!.toString())}, { })
        )
    }
}