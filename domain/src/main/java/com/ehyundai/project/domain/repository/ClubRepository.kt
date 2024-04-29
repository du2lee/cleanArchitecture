package com.ehyundai.project.domain.repository

import android.net.Uri
import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.domain.model.ClubInfo
import com.ehyundai.project.domain.model.ClubResponseForDomain
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

    fun getClubInfo(clubNo: Int): Single<ClubInfo>

    fun createClub(token: String, clubNm: String, clubDesc: String, clubImg: Uri): Single<ClubResponseForDomain>
}