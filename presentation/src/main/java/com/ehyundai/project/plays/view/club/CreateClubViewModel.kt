package com.ehyundai.project.plays.view.club

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehyundai.project.domain.model.PostEntity
import com.ehyundai.project.domain.usecase.GetClubsUseCase
import com.ehyundai.project.plays.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.inject.Inject

@HiltViewModel
class CreateClubViewModel @Inject constructor(private  val getClubsUseCase: GetClubsUseCase): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val clubNm: MutableLiveData<String> = MutableLiveData("")
    val clubDesc: MutableLiveData<String> = MutableLiveData("")
    val clubImg: MutableLiveData<Uri?> = MutableLiveData<Uri?>()

    fun setCamera(context: Context, image: Bitmap){
        try {
            val file = File(context.externalCacheDir, "sample_image.jpg")
            val outputStream: OutputStream = FileOutputStream(file)
            image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            clubImg.postValue(Uri.fromFile(file))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun setGallery(image: Uri?){
        clubImg.value = image
    }

    fun creatClub(context: Context){
        val token = Util.getPreference(context, "accessToken", "")?: ""

        var name = clubNm.value
        var desc = clubDesc.value
        var uri = clubImg.value

        if(!name.isNullOrBlank() and !desc.isNullOrBlank()){
            compositeDisposable.add(
                getClubsUseCase.createClub(token, name!!, desc!!, uri!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { response ->
                        Log.i("duhuiTest", response.toString())
                    }
            )
        }
    }
}