package com.ehyundai.project.data.repository.member.remote

import android.util.Log
import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.api.RetrofitClient
import com.ehyundai.project.data.model.auth.LoginResponse
import com.ehyundai.project.data.model.board.BoardResponse
import com.ehyundai.project.data.model.members.AuthResponse
import com.ehyundai.project.data.model.members.BaseResponse
import com.ehyundai.project.plays.util.Constants
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MemberRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface): MemberRemoteDataSource {
    override fun getAuthCode(email: String): Single<AuthResponse> {
        return apiInterface.getAuthCode(email)
    }

    override fun verifyAuthCode(email: String, authCode: String): Single<BaseResponse> {
        return apiInterface.verifyAuthCode(email, authCode)
    }

    override fun checkDuplicatedNickname(nickname: String): Single<BaseResponse> {
        return apiInterface.checkDuplicatedNickname(nickname)
    }

    override fun signUp(email: String, pwd: String, nickname: String, companyNo: String): Single<BaseResponse> {
        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("pwd", pwd)
        jsonObject.put("nickname", nickname)
        jsonObject.put("companyNo", companyNo)
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString())
        return apiInterface.signUp(requestBody)
    }

    override fun login(email: String, pwd: String): Single<LoginResponse> {
        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("pwd", pwd)
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString())
        return Single.create { emitter ->
            val call = apiInterface.login(requestBody)
            call.enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body() // 응답 데이터 가져오기
                        if (data != null) {
                            emitter.onSuccess(LoginResponse(data.status, data.code, data.data))
                        } else {
                            emitter.onSuccess(
                                LoginResponse(Constants.ERROR_STATUS, Constants.ERROR_CODE, null)
                            )
                        }
                    } else {
                        emitter.onSuccess(
                            LoginResponse(Constants.ERROR_STATUS, Constants.ERROR_CODE, null)
                        )
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    emitter.onSuccess( LoginResponse(Constants.ERROR_STATUS, Constants.ERROR_CODE, null) )
                }
            })
        }
    }

//    override fun editInfo(
//        pwd: String?,
//        nickname: String?,
//        companyNo: String?,
//        part: String?,
//        area: String?
//    ): Single<AuthResponse> {
//        val jsonObject = JSONObject()
//        if(!pwd.isNullOrBlank())
//            jsonObject.put("pwd", pwd)
//
//        jsonObject.put("nickname", nickname)
//        jsonObject.put("companyNo", companyNo)
//        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString())
//        return apiInterface.signUp(requestBody)
//    }

}