package com.ehyundai.project.domain.model

data class Login (
    val email : String?,
    val auth : String?,
    val accessToken : String?,
    val refreshToken: String?
)