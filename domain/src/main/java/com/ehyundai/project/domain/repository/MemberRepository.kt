package com.ehyundai.project.domain.repository

import com.ehyundai.project.domain.model.AuthCode
import com.ehyundai.project.domain.model.Login
import io.reactivex.Single

interface MemberRepository {
    fun getAuthCode(email: String): Single<AuthCode>
    fun verifyAuthCode(email: String, authCode: String): Single<AuthCode>
    fun checkDuplicatedNickname(nickname: String): Single<AuthCode>
    fun signUp(email: String, pwd: String, nickname: String): Single<AuthCode>

    fun login(email: String, pwd: String): Single<Login>
}