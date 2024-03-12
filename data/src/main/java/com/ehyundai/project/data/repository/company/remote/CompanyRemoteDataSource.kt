package com.ehyundai.project.data.repository.company.remote

import com.ehyundai.project.data.model.company.CompanyResponse
import io.reactivex.Single

interface CompanyRemoteDataSource {
    fun getCompany(): Single<CompanyResponse>
}