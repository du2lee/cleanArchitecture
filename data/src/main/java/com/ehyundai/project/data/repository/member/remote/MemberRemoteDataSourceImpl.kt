package com.ehyundai.project.data.repository.member.remote

import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.model.members.AuthResponse
import com.ehyundai.project.data.model.members.BaseResponse
import io.reactivex.Single
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
        return apiInterface.signUp(email, pwd, nickname)
    }
}