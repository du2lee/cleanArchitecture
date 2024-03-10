package com.ehyundai.project.data.repository.search.local

import com.ehyundai.project.data.model.club.ClubEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Local 에 저장되어 있는 Club Data 를 사용하기 위한 interface
 * DataSourceImpl 에서 구현된다.
 *
 * DataSource 는 Data layer 의 CRUD 를 호출하는 interface.
 * DataSourceImpl 은 DataSource interface 의 구현부.
 */
//@DefineComponent(parent = ActivityComponent::class)
interface ClubLocalDataSource {
    fun insertClubs(clubs: List<ClubEntity>): Completable
    fun getAllClubs(): Single<List<ClubEntity>>
    fun getSearchClubs(clubNo: String): Single<List<ClubEntity>>
    fun deleteAllClubs(): Completable
}