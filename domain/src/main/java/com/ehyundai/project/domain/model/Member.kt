package com.ehyundai.project.domain.model

data class Member (
    val memberNo : Int,
    val memberNm : String,
    val nickname : String,
    val companyNm : String,
    val role : String,
    val profileImg : String,
    val introduction : String,
)

data class AuthCode (
    val status : String,
    val code : String,
    val authCode : String,
)