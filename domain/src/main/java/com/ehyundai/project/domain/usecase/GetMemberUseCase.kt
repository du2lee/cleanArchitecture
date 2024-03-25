package com.ehyundai.project.domain.usecase

import com.ehyundai.project.domain.model.AuthCode
import com.ehyundai.project.domain.repository.MemberRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMemberUseCase @Inject constructor(private val repository: MemberRepository) {
    fun getAuthCode(email: String): Single<AuthCode> = repository.getAuthCode(email)
    fun verifyAuthCode(email: String, authCode: String) = repository.verifyAuthCode(email, authCode)
    fun checkDuplicatedNickname(nickname: String) = repository.checkDuplicatedNickname(nickname)
    fun signUp(email: String, pwd: String, nickname: String) = repository.signUp(email, pwd, nickname)
}