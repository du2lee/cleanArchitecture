package com.ehyundai.project.domain.repository

import com.ehyundai.project.domain.model.Club
import io.reactivex.Flowable

interface TestRepository {
    fun getClubs() : Flowable<ArrayList<Club>>
}