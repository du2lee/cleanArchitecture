package com.ehyundai.project.data.repository.member.remote

import android.util.Log
import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.api.RetrofitClient
import com.ehyundai.project.data.model.members.AuthResponse
import com.ehyundai.project.data.model.members.BaseResponse
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

    override fun signUp(email: String, pwd: String, nickname: String): Single<BaseResponse> {
        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("pwd", pwd)
        jsonObject.put("nickname", nickname)
        val requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString())
        return apiInterface.signUp(requestBody)
    }

}