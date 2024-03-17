package com.ehyundai.project.data.repository.club.local

import com.ehyundai.project.data.db.club.ClubDao
import com.ehyundai.project.data.model.club.ClubEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ClubLocalDataSourceImpl @Inject constructor(private val clubDao: ClubDao) : ClubLocalDataSource {
    override fun insertClubs(clubs: List<ClubEntity>): Completable =
        clubDao.insertClub(clubs)

    override fun getAllClubs(): Single<List<ClubEntity>> =
        clubDao.getAllClubs()

    override fun getSearchClubs(company: String): Single<List<ClubEntity>> =
        clubDao.getClubsByCompany(company)

    override fun deleteAllClubs(): Completable =
        clubDao.deleteAllClubs()

}