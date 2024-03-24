package com.ehyundai.project.data.repository.club.remote

import android.util.Log
import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.model.club.ClubInfoResponse
import com.ehyundai.project.data.model.club.ClubResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * DataSource 에서 선언한 Interface 의 구현부.
 * 해당 Interface 를 상속받아 사용한다.
 * Api 를 통해 Data 를 가져오는 것이기 때문에 ApiInterface 를 사용한다.
 *
 * @param apiInterface api 호출을 위한 Interface
 */
class ClubRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) :
    ClubRemoteDataSource {
    override fun getSearchClubs(companyNo : Int): Single<ClubResponse> {
        return apiInterface.getSearchClubs(companyNo)
    }

    override fun getSearchAll(): Single<ClubResponse> {
        return apiInterface.getSearchClubAll()
    }

    override fun getSearchClubInfo(clubNo: Int): Single<ClubInfoResponse> {
        return apiInterface.getSearchClubInfo(clubNo)
    }
}