package com.ehyundai.project.data.repository.member.remote

import com.ehyundai.project.data.model.auth.LoginResponse
import com.ehyundai.project.data.model.members.AuthResponse
import com.ehyundai.project.data.model.members.BaseResponse
import io.reactivex.Single

interface MemberRemoteDataSource {
    fun getAuthCode(email: String): Single<AuthResponse>
    fun verifyAuthCode(email: String, authCode: String): Single<BaseResponse>
    fun checkDuplicatedNickname(nickname: String): Single<BaseResponse>
    fun signUp(email: String, pwd: String, nickname: String, companyNo: String): Single<BaseResponse>
    fun login(email: String, pwd: String): Single<LoginResponse>
}