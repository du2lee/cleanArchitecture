package com.ehyundai.project.data.repository.search.remote

import com.ehyundai.project.data.model.club.ClubResponse
import io.reactivex.Single

/**
 * Api 호출을 통해 Club Data 를 가져오기 위한 interface
 * DataSourceImpl 에서 구현된다.
 */
//@DefineComponent(parent = ActivityComponent::class)
interface ClubRemoteDataSource {
    fun getSearchClubs(): Single<ClubResponse>
}