package com.ehyundai.project.data.api

import com.ehyundai.project.data.model.club.ClubInfoResponse
import com.ehyundai.project.data.model.club.ClubResponse
import com.ehyundai.project.data.model.company.CompanyResponse
import com.ehyundai.project.data.model.members.AuthResponse
import com.ehyundai.project.data.model.members.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    // club
    @GET("club/list/all")
    fun getSearchClubAll(): Single<ClubResponse>

    @GET("club/list")
    fun getSearchClubs(companyNo : Int): Single<ClubResponse>

    @GET("club/info")
    fun getSearchClubInfo(
        @Query("clubNo") clubNo: Int
    ): Single<ClubInfoResponse>

    // company
    @GET("company/list")
    fun getCompany(): Single<CompanyResponse>

    // member
    @POST("members/emails/send-authcode")
    fun getAuthCode(
        @Query("email") email: String
    ): Single<AuthResponse>


    @GET("members/emails/verifications")
    fun verifyAuthCode(
        @Query("email") email: String,
        @Query("authCode") authCode: String
    ): Single<BaseResponse>

    @POST("members/check-duplicated-nickname")
    fun checkDuplicatedNickname(
        @Query("nickname") nickname: String,
    ): Single<BaseResponse>

    @POST("members/signup")
    fun signUp(
        @Query("email") email: String,
        @Query("pwd") pwd: String,
        @Query("nickname") nickname: String,
//        @Query("company") company: String
    ): Single<BaseResponse>
}