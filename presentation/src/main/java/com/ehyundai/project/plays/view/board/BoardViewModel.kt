package com.ehyundai.project.plays.view.board

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehyundai.project.domain.model.PostEntity
import com.ehyundai.project.domain.usecase.GetBoardUseCase
import com.ehyundai.project.plays.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val getBoardUseCase: GetBoardUseCase
): ViewModel(){
    private val compositeDisposable = CompositeDisposable()

    private val _boardList = MutableLiveData<ArrayList<PostEntity>>()
    val boardList: LiveData<ArrayList<PostEntity>> get() = _boardList

    init {

    }

    fun getBoard(context: Context, clubNo: String){
        val token = Util.getPreference(context, "accessToken", "")?: ""

        compositeDisposable.add(
            getBoardUseCase.getPost(token, clubNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { boards ->
                    Log.i("duhuiViewModel", boards.toString())
                    _boardList.value = boards.data as ArrayList<PostEntity>
                }
        )
    }

    fun getBoard(context: Context){
        val token = Util.getPreference(context, "accessToken", "")?: ""

        compositeDisposable.add(
            getBoardUseCase.getPost(token)
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

    fun getBoardList(): ArrayList<PostEntity>? {
        return boardList.value
    }
}