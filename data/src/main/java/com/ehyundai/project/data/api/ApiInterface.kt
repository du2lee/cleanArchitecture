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
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiInterface {
    /**
     * Member
     */
    @POST("members/signup")
    fun signUp(@Body requestBody: RequestBody): Single<BaseResponse>

    @POST("members/emails/send-authcode")
    fun getAuthCode(
        @Query("email") email: String
    ): Single<AuthResponse>

    // TODO output 수정
    @PATCH("members/profile-update")
    fun updateProfile(
        @Query("memberNm") memberNm: String?,
        @Query("nickname") nickname: String?,
        @Query("team") team: String?,
        @Query("area") area: String?,
        @Query("birth") birth: String?,
        @Query("profile") profile: String?,
        @Query("profileImg") profileImg: String?,
    ): Single<AuthResponse>

    // TODO output 수정
    @GET("members/profile")
    fun getProfile(): Single<AuthResponse>

    @GET("members/emails/verifications")
    fun verifyAuthCode(
        @Query("email") email: String,
        @Query("authCode") authCode: String
    ): Single<BaseResponse>

    @GET("members/check-duplicated-nickname")
    fun checkDuplicatedNickname(
        @Query("nickname") nickname: String,
    ): Single<BaseResponse>

    /**
     * POST
     */
    // TODO output 수정
    @GET("clubPost")
    fun getPost(
        @Query("postNo") clubNo: String,
    ): Call<BoardResponse>

    // TODO output 수정
    @POST("clubPost")
    fun setPost(
        @Query("clubNo") clubNo: String,
        @Query("companyNo") companyNo: String,
        @Query("postTitle") postTitle: String,
        @Query("postContent") postContent: String,
    ): Call<BoardResponse>

    // TODO output 수정
    @DELETE("clubPost")
    fun deletePost(
        @Query("postNo") postNo: String,
    ): Call<BoardResponse>

    // TODO output 수정
    @PATCH("clubPost")
    fun patchPost(
        @Query("postNo") postNo: String?,
        @Query("clubNo") clubNo: String?,
        @Query("companyNo") companyNo: String?,
        @Query("postTitle") postTitle: String?,
        @Query("postContent") postContent: String?,
        @Query("likeCnt") likeCnt: String?,
    ): Call<BoardResponse>

    // TODO output 수정
    @POST("clubPost/like")
    fun setPostLike(
        @Query("postNo") postNo: String,
    ): Call<BoardResponse>

    // TODO output 수정
    @GET("clubPost/myclub-post-list")
    fun getMyClubPost(): Call<BoardResponse>

    @GET("clubPost/list")
    fun getPostList(
        @Query("clubNo") clubNo: String
    ): Call<BoardResponse>

    /**
     * Auth
     */
    @POST("auth/login")
    fun login(@Body requestBody: RequestBody): Call<LoginResponse>

    // TODO input, output 수정
    @POST("auth/refresh")
    fun refresh(@Body requestBody: RequestBody): Single<LoginResponse>

    // TODO input, output 수정
    @DELETE("auth/login")
    fun logout(@Body requestBody: RequestBody): Single<LoginResponse>

    /**
     * Club
     */
    // TODO input, output 수정
    @Multipart
    @POST("club")
    fun postClub(
        @Part("companyNo") companyNo: RequestBody,
        @Part("clubNm") clubNm: RequestBody,
        @Part("clubDesc") clubDesc: RequestBody,
        @Part clubImg: MultipartBody.Part,
    ): Call<ClubResponse>

    // TODO output 수정
    @POST("club/signup")
    fun signUpClub(
        @Query("clubNo") clubNo: Int,
        @Query("introduction") introduction: String,
    ):Single<ClubResponse>

    // TODO input, output 수정
    @POST("club/approval")
    fun approvalClub(
        @Query("clubNo") clubNo: Int,
        @Query("memberNoList") introduction: String,
    ):Single<ClubResponse>

    // TODO output 수정
    @GET("club/request-members")
    fun getRequestMembers(
        clubNo: Int
    ): Single<ClubResponse>

    // TODO output 수정
    @GET("club/my-club-list")
    fun getMyClubs(): Single<ClubResponse>


    // TODO output 수정
    @GET("club/member-list")
    fun getClubMembers(
        clubNo: Int
    ): Single<ClubResponse>

    @GET("club/list")
    fun getClubs(companyNo : Int): Single<ClubResponse>

    @GET("club/list/all")
    fun getClubAll(): Single<ClubResponse>

    @GET("club/info")
    fun getClubInfo(
        @Query("clubNo") clubNo: Int
    ): Single<ClubInfoResponse>

    // TODO output 수정
    @GET("club/club-member-check")
    fun getClubMemberStatus(
        clubNo: Int
    ): Single<ClubResponse>

    /**
     * Comment
     */
    // TODO output 수정
    @POST("comment")
    fun postComment(
        postNo: Int,
        commentContent: String
    ): Single<ClubResponse>

    // TODO output 수정
    @DELETE("comment")
    fun deleteComment(
        commentNo: Int,
        postNo: Int,
        commentContent: String
    ): Single<ClubResponse>

    // TODO output 수정
    @PATCH("comment")
    fun patchComment(
        commentNo: Int,
        postNo: Int,
        commentContent: String
    ): Single<ClubResponse>

    // TODO output 수정
    @GET("comment/list")
    fun getComments(
        postNo: Int,
    ): Single<ClubResponse>

    /**
     * Company
     */
    // TODO input, output 수정
    @POST("company/list")
    fun postCompany(
        companyNm: String,
        companyImg: MultipartBody.Part
    ): Single<CompanyResponse>

    // TODO output 수정
    @DELETE("company/list")
    fun deleteCompany(
        companyNo: Int
    ): Single<CompanyResponse>

    @GET("company/list")
    fun getCompany(): Single<CompanyResponse>
}