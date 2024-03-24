package com.ehyundai.project.data.mapper

import com.ehyundai.project.data.model.club.ClubEntity
import com.ehyundai.project.data.model.club.ClubInfoEntity
import com.ehyundai.project.data.model.company.CompanyEntity
import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.domain.model.ClubInfo
import com.ehyundai.project.domain.model.Company
import io.reactivex.Single

/**
 * Data Entity to Data Model
 * Data Layer 에서는 Data Entity 로 받아서 사용하지만, Domain, Presentation Layer 에서는 Data Model 로 사용한다.
 * 즉, Mapper 는 Data Layer 에 존재하면서 다른 계층으로 Data 를 전달할 때, 사용하는 Data Model 에 맞춰서 변환하여 던지는 역할.
 *
 */
fun mapperToClub(clubs: List<ClubEntity>): List<Club> {
    return clubs.toList().map {
        Club(
            it.clubNo,
            it.company,
            it.name,
            it.logo,
            it.members,
            it.date
        )
    }
}

fun mapperToClubInfo(club: ClubInfoEntity): ClubInfo {
    return ClubInfo(
        club.clubNo,
        club.company?: "미지정",
        club.name,
        club.clubDesc,
        club.logo,
        club.date,
        club.members)
}

fun mapperToCompany(companys: List<CompanyEntity>): List<Company> {
    return companys.toList().map {
        Company(
            it.companyNo,
            it.name,
            it.imgPath
        )
    }
}
