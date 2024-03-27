package com.ehyundai.project.data.repository.member

import com.ehyundai.project.data.mapper.mapperToAuthCode
import com.ehyundai.project.data.mapper.mapperToLogin
import com.ehyundai.project.data.mapper.mapperToReponse
import com.ehyundai.project.data.repository.member.remote.MemberRemoteDataSource
import com.ehyundai.project.domain.model.AuthCode
import com.ehyundai.project.domain.model.Login
import com.ehyundai.project.domain.repository.MemberRepository
import io.reactivex.Single
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberRemoteDataSource: MemberRemoteDataSource
) : MemberRepository {
    override fun getAuthCode(email: String): Single<AuthCode> {
        return memberRemoteDataSource.getAuthCode(email).flatMap {
            Single.just(mapperToAuthCode((it)))
        }
    }

    override fun verifyAuthCode(email: String, authCode: String): Single<AuthCode> {
        return memberRemoteDataSource.verifyAuthCode(email, authCode).flatMap {
            Single.just(mapperToReponse((it)))
        }
    }

    override fun checkDuplicatedNickname(nickname: String): Single<AuthCode> {
        return memberRemoteDataSource.checkDuplicatedNickname(nickname).flatMap {
            Single.just(mapperToReponse((it)))
        }
    }

    override fun signUp(email: String, pwd: String, nickname: String): Single<AuthCode> {
        return memberRemoteDataSource.signUp(email, pwd, nickname).flatMap {
            Single.just(mapperToReponse((it)))
        }
    }

    override fun login(email: String, pwd: String): Single<Login> {
        return memberRemoteDataSource.login(email, pwd).flatMap {
            Single.just(mapperToLogin(it.data))
        }
    }
}