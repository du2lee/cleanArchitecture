package com.ehyundai.project.domain.model

data class Post (
    val status: String,
    val code: String,
    val data: List<PostEntity>?,
)

data class PostEntity (
    val postNo : String?,
    val nickname : String?,
    val companyNm : String?,
    val postTitle : String?,
    val postContent : String?,
    val likeCnt : String?,
//    val commentList : List<CommentEntityForDomain>?,
    val createdDate : String?,
    val postImg : List<PostImg>?,
    val commentCount : String?,
)

data class CommentEntityForDomain (
    val postNo : String?,
)

data class PostImg (
    val postImgNo : String?,
    val postNo : String?,
    val url : String?,
)