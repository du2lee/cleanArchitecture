package com.ehyundai.project.domain.repository

import com.ehyundai.project.domain.model.Company
import io.reactivex.Flowable
import io.reactivex.Single

interface CompanyRepository {
    fun getCompany(): Flowable<List<Company>>
    fun getRemoteCompany(): Single<List<Company>>
}