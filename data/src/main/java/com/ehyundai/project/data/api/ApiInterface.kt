package com.ehyundai.project.data.api

import com.ehyundai.project.data.model.auth.LoginResponse
import com.ehyundai.project.data.model.board.BoardResponse
import com.ehyundai.project.data.model.club.ClubInfoResponse
import com.ehyundai.project.data.model.club.ClubResponse
import com.ehyundai.project.data.model.company.CompanyResponse
import com.ehyundai.project.data.model.members.AuthResponse
import com.ehyundai.project.data.model.members.BaseResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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

//    /**
//     * 모바일 여권 정보 저장 함수
//     */
//    @Multipart
//    @POST("club")
//    fun saveEPassportInfo(
//        @Part("Authorization") mbshNo: RequestBody,
//        @Part("companyNo") piKey: RequestBody,
//        @Part("clubNm") givenName: RequestBody,
//        @Part("clubDesc") surName: RequestBody,
//        @Part clubImg : MultipartBody.Part,
//    ): Call<EPassportRequest>



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

    @GET("members/check-duplicated-nickname")
    fun checkDuplicatedNickname(
        @Query("nickname") nickname: String,
    ): Single<BaseResponse>

    @POST("members/signup")
    fun signUp(@Body requestBody: RequestBody): Single<BaseResponse>

    // auth
    @POST("auth/login")
    fun login(@Body requestBody: RequestBody): Single<LoginResponse>

    // board
    @GET("clubPost/list")
    fun getPost(
        @Query("clubNo") clubNo: String
    ): Call<BoardResponse>

}