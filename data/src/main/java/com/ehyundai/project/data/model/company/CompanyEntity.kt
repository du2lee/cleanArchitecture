package com.ehyundai.project.data.model.company

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "company")
data class CompanyEntity (
    @PrimaryKey
    @SerializedName("companyNo")
    val companyNo : Int,
    @SerializedName("companyNm")
    val name : String,
    @SerializedName("companyImg")
    val imgPath : String,
)