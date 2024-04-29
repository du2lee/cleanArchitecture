package com.ehyundai.project.plays.view.club

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehyundai.project.domain.model.ClubInfo
import com.ehyundai.project.domain.model.PostEntity
import com.ehyundai.project.domain.usecase.GetBoardUseCase
import com.ehyundai.project.domain.usecase.GetClubsUseCase
import com.ehyundai.project.plays.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ClubViewModel @Inject constructor(
    private val getClubsUseCase: GetClubsUseCase,
    private val getBoardUseCase: GetBoardUseCase
): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _clubInfo = MutableLiveData<ClubInfo>()
    val clubInfo: LiveData<ClubInfo> get() = _clubInfo

    private val _boardList = MutableLiveData<ArrayList<PostEntity>>()
    val boardList: LiveData<ArrayList<PostEntity>> get() = _boardList

    val clubDate = MutableLiveData<String>()



    fun getClubInfo(clubNo: Int){
        compositeDisposable.add(
            getClubsUseCase.clickClub(clubNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ club ->
                    _clubInfo.value = club
                    clubDate.value = club.date!!.substring(0, 10)
               }, { })
        )
    }

    fun getBoard(context: Context){
        val token = Util.getPreference(context, "accessToken", "")?: ""

        compositeDisposable.add(
            getBoardUseCase.getPost(token, clubInfo.value!!.clubNo.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { boards ->
                    if(boards.data.isNullOrEmpty())
                        _boardList.value = arrayListOf()
                    else
                        _boardList.value = boards.data as ArrayList<PostEntity>
                }
        )
    }
}