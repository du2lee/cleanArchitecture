package com.ehyundai.project.domain.repository

import com.ehyundai.project.domain.model.Club
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * UseCase 에 필요한 Interface 를 선한한 Repository.
 * RepositoryImpl 에서 구현되며, 실제 필요한 데이터를 가져온다.
 */

interface ClubRepository {
    fun getClubs(): Flowable<List<Club>>
    fun getRemoteClubs(): Single<List<Club>>
    fun getClub(company: String) : Flowable<List<Club>>
    fun getRemoteClub(companyNo: Int): Single<List<Club>>
}