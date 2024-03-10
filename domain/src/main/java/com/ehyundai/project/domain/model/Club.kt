package com.ehyundai.project.domain.model

/**
 * 실제 사용하는 Data Class.
 *
 * ClubEntity 와 동일한 데이터 모델이지만, 필요에 따라 필요한 데이터만 선언하여 사용하면 된다.
 */
data class Club(
    val clubNo : String,
    val company : String,
    val name : String,
    val logo : String,
    val members : String,
    val date : String,
)