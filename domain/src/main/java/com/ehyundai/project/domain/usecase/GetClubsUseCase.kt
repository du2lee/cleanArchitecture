package com.ehyundai.project.domain.usecase

import android.net.Uri
import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.domain.model.ClubInfo
import com.ehyundai.project.domain.repository.ClubRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetClubsUseCase @Inject constructor(private val repository: ClubRepository) {
    operator fun invoke(): Flowable<List<Club>> = repository.getClubs()

    fun clickCompany(company: String): Flowable<List<Club>> = repository.getClub(company)

    fun clickClub(clubNo: Int): Single<ClubInfo> = repository.getClubInfo(clubNo)

    fun createClub(token: String, clubNm: String, clubDesc: String, clubImg: Uri) = repository.createClub(token, clubNm, clubDesc, clubImg)
}