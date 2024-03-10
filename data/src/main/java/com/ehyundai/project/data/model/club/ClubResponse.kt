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