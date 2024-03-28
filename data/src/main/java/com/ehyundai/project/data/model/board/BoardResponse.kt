package com.ehyundai.project.data.model.board

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BoardResponse (
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: List<BoardEntity>,
)

@Serializable
data class BoardEntity (
    @SerializedName("postNo")
    val postNo : String,
    @SerializedName("nickname")
    val nickname : String,
    @SerializedName("companyNm")
    val companyNm : String,
    @SerializedName("postTitle")
    val postTitle : String,
    @SerializedName("postContent")
    val postContent : String,
    @SerializedName("likeCnt")
    val likeCnt : String,
    @SerializedName("commentList")
    val commentList : List<CommentEntity>,
    @SerializedName("createdDate")
    val createdDate : String,
    @SerializedName("postImg")
    val postImg : List<BoardImg>,
    @SerializedName("commentCount")
    val commentCount : String,
)

@Serializable
data class CommentEntity (
    @SerializedName("postNo")
    val postNo : String,
)

@Serializable
data class BoardImg (
    @SerializedName("postImgNo")
    val postImgNo : String,
    @SerializedName("postNo")
    val postNo : String,
    @SerializedName("url")
    val url : String,
)