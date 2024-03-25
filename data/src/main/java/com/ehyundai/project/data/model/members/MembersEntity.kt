package com.ehyundai.project.data.model.members

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MemberEntity (
    @SerializedName("memberNo")
    val memberNo : Int,
    @SerializedName("memberNm")
    val memberNm : String,
    @SerializedName("nickname")
    val nickname : String,
    @SerializedName("companyNm")
    val companyNm : String,
    @SerializedName("role")
    val role : String,
    @SerializedName("profileImg")
    val profileImg : String,
    @SerializedName("introduction")
    val introduction : String,
)

@Serializable
data class AuthResponse (
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: String,
)

@Serializable
data class BaseResponse (
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val data: String,
)