package com.ehyundai.project.data.mapper

import com.ehyundai.project.data.model.club.ClubEntity
import com.ehyundai.project.domain.model.Club

/**
 * Data Entity to Data Model
 * Data Layer 에서는 Data Entity 로 받아서 사용하지만, Domain, Presentation Layer 에서는 Data Model 로 사용한다.
 * 즉, Mapper 는 Data Layer 에 존재하면서 다른 계층으로 Data 를 전달할 때, 사용하는 Data Model 에 맞춰서 변환하여 던지는 역할.
 *
 * @param clubs Data Model 로 변환 할 Entity
 * @return Data Model
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