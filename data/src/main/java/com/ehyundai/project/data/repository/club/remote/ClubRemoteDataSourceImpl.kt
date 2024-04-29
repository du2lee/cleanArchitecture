package com.ehyundai.project.data.repository.club.remote

import android.net.Uri
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.api.RetrofitClient
import com.ehyundai.project.data.model.club.ClubInfoResponse
import com.ehyundai.project.data.model.club.ClubResponse
import com.ehyundai.project.data.model.club.ClubResponse2
import com.ehyundai.project.plays.util.Constants
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import javax.inject.Inject

/**
 * DataSource 에서 선언한 Interface 의 구현부.
 * 해당 Interface 를 상속받아 사용한다.
 * Api 를 통해 Data 를 가져오는 것이기 때문에 ApiInterface 를 사용한다.
 *
 * @param apiInterface api 호출을 위한 Interface
 */
class ClubRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) :
    ClubRemoteDataSource {
    override fun getSearchClubs(companyNo : Int): Single<ClubResponse> {
        return apiInterface.getClubs(companyNo)
    }

    override fun getSearchAll(): Single<ClubResponse> {
        return apiInterface.getClubAll()
    }

    override fun getSearchClubInfo(clubNo: Int): Single<ClubInfoResponse> {
        var value = apiInterface.getClubInfo(clubNo)
        Log.i("duhuiClubInfo", value.toString())
        return value
    }

    override fun createClub(token: String, clubNm: String, clubDesc: String, clubImg: Uri): Single<ClubResponse2> {
        val imageFile = File(clubImg.path)
        val requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile)
        val imagePart = MultipartBody.Part.createFormData("imgData", "club_logo.jpg", requestBody)
        val clubNamePart = RequestBody.create(MediaType.parse("text/plain"), clubNm)
        val clubDescPart = RequestBody.create(MediaType.parse("text/plain"), clubDesc)

        return Single.create { emitter ->
            val call =
                RetrofitClient.getApiService(token).postClub(clubNamePart, clubDescPart, imagePart)
            call.enqueue(object : Callback<ClubResponse2> {
                override fun onResponse(
                    call: Call<ClubResponse2>,
                    response: Response<ClubResponse2>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body() // 응답 데이터 가져오기
                        if (data != null) {
                            emitter.onSuccess(ClubResponse2(data.status, data.code, data.data))
                            Log.i("duhui1", data.toString())
                        } else {
                            emitter.onSuccess(
                                ClubResponse2(
                                    Constants.ERROR_STATUS,
                                    Constants.ERROR_CODE,
                                    ""
                                )
                            )
                        }
                    } else {
                        Log.i("duhui2", response.code().toString())
                        Log.i("duhui2", response.message().toString())
                        emitter.onSuccess(
                            ClubResponse2(
                                Constants.ERROR_STATUS,
                                Constants.ERROR_CODE,
                                ""
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<ClubResponse2>, t: Throwable) {
                    // 요청 실패 시 처리
                    Log.i("duhui3", "요청 실패요!")
                    Log.i("duhui3", t.message.toString())
                    Log.i("duhui3", t.cause.toString())
                    emitter.onSuccess(
                        ClubResponse2(
                            Constants.ERROR_STATUS,
                            Constants.ERROR_CODE,
                            ""
                        )
                    )
                }
            })
        }
    }
}