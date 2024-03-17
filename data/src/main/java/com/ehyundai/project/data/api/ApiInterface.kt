package com.ehyundai.project.data.api

import com.ehyundai.project.data.model.club.ClubResponse
import com.ehyundai.project.data.model.company.CompanyResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    // club
    @GET("club/list/all")
    fun getSearchClubAll(): Single<ClubResponse>

    @GET("club/list")
    fun getSearchClubs(companyNo : Int): Single<ClubResponse>

    // company
    @GET("company/list")
    fun getCompany(): Single<CompanyResponse>
}