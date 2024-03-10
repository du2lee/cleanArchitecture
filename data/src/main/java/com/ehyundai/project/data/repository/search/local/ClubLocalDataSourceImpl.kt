package com.ehyundai.project.data.repository.search.local

import com.ehyundai.project.data.db.ClubDao
import com.ehyundai.project.data.model.club.ClubEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ClubLocalDataSourceImpl @Inject constructor(private val clubDao: ClubDao) : ClubLocalDataSource {
    override fun insertClubs(clubs: List<ClubEntity>): Completable =
        clubDao.insertClub(clubs)

    override fun getAllClubs(): Single<List<ClubEntity>> =
        clubDao.getAllClubs()

    override fun getSearchClubs(clubNo: String): Single<List<ClubEntity>> =
        clubDao.getClubsByClubNo(clubNo)

    override fun deleteAllClubs(): Completable =
        clubDao.deleteAllClubs()

}