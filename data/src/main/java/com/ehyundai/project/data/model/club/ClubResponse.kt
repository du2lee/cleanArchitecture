package com.ehyundai.project.data.model.club

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ClubResponse (
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: List<ClubEntity>,
)


@Serializable
data class ClubInfoResponse (
    @SerializedName("status")
    val status: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("data")
    val data: ClubInfoEntity?,
)

@Serializable
data class ClubResponse2 (
    @SerializedName("status")
    val status: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("data")
    val data: String?,
)