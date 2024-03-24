package com.ehyundai.project.data.model.club

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "club")
data class ClubEntity (
    @PrimaryKey
    @SerializedName("clubNo")
    val clubNo : String,
    @SerializedName("companyNm")
    val company : String,
    @SerializedName("clubNm")
    val name : String,
    @SerializedName("clubImg")
    val logo : String,
    @SerializedName("memberCount")
    val members : String,
    @SerializedName("createdDate")
    val date : String,
)

@Serializable
data class ClubInfoEntity (
    @SerializedName("clubNo")
    val clubNo : Int,
    @SerializedName("companyNm")
    val company : String,
    @SerializedName("clubNm")
    val name : String,
    @SerializedName("clubDesc")
    val clubDesc : String,
    @SerializedName("clubImg")
    val logo : String,
    @SerializedName("createdDate")
    val date : String,
    @SerializedName("memberList")
    val members : List<String>,
)