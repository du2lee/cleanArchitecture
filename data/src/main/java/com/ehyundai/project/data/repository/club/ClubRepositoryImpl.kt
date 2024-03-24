package com.ehyundai.project.data.repository.club

import android.util.Log
import com.ehyundai.project.data.mapper.mapperToClub
import com.ehyundai.project.data.mapper.mapperToClubInfo
import com.ehyundai.project.data.repository.club.local.ClubLocalDataSource
import com.ehyundai.project.data.repository.club.remote.ClubRemoteDataSource
import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.domain.model.ClubInfo
import com.ehyundai.project.domain.repository.ClubRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class ClubRepositoryImpl @Inject constructor(
    private val clubLocalDataSource: ClubLocalDataSource,
    private val clubRemoteDataSource: ClubRemoteDataSource
): ClubRepository{
    override fun getClubs(): Flowable<List<Club>> {
        return clubLocalDataSource.getAllClubs()
            .onErrorReturn { listOf() }
            .flatMapPublisher { localClubs ->
                if(localClubs.isEmpty()) {
                    getRemoteClubs()
                        .toFlowable()
                        .onErrorReturn { listOf() }
                } else {
                    val local = Single.just(mapperToClub(localClubs))
                    val remote = getRemoteClubs()
                        .onErrorResumeNext { local }
                    Single.concat(local, remote)
                }
            }
    }

    override fun getRemoteClubs(): Single<List<Club>> {
        return clubRemoteDataSource.getSearchAll()
            .flatMap {
                // insertClubs 는 ClubEntity 로 localDB에 insert.
                // andThen 연산자를 통해 localDB에 insert 한 Data 들을 Mapper 클래스를 사용하여 Club type 으로 mapping 하고 해당 list 를 return
                clubLocalDataSource.insertClubs(it.data).
                        andThen(Single.just(mapperToClub(it.data)))
            }
    }

    override fun getClub(company: String): Flowable<List<Club>> {
        return clubLocalDataSource.getSearchClubs(company)
            .onErrorReturn { listOf() }
            .flatMapPublisher { localClubs -> Single.just(mapperToClub(localClubs)).toFlowable() }
    }

    override fun getRemoteClub(companyNo: Int): Single<List<Club>> {
        return clubRemoteDataSource.getSearchClubs(companyNo)
            .flatMap {
                Single.just(mapperToClub(it.data))
            }
    }

    override fun getClubInfo(clubNo: Int): Single<ClubInfo> {
        return clubRemoteDataSource.getSearchClubInfo(clubNo).flatMap {
            Single.just(mapperToClubInfo((it.data)))
        }
    }
}