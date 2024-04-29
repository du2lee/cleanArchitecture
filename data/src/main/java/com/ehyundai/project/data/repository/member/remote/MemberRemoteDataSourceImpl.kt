package com.ehyundai.project.data.repository.member.remote

import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.model.auth.LoginResponse
import com.ehyundai.project.data.model.members.AuthResponse
import com.ehyundai.project.data.model.members.BaseResponse
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
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
        return apiInterface.login(requestBody)
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