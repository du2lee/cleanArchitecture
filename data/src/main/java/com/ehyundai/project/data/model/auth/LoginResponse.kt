package com.ehyundai.project.data.model.auth

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse (
    @SerializedName("status")
    val status: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("data")
    val data: LoginEntity?,
)

@Serializable
data class LoginEntity (
    @SerializedName("email")
    val email : String,
    @SerializedName("auth")
    val auth : String,
    @SerializedName("accessToken")
    val accessToken : String,
    @SerializedName("refreshToken")
    val refreshToken : String,
)