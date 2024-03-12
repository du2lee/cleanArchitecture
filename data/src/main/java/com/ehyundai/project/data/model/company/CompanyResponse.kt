package com.ehyundai.project.data.model.company

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyResponse (
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: List<CompanyEntity>,
)