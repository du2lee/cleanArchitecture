package com.ehyundai.project.data.repository.company.remote

import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.model.company.CompanyResponse
import io.reactivex.Single
import javax.inject.Inject

class CompanyRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) : CompanyRemoteDataSource {
    override fun getCompany(): Single<CompanyResponse> {
        return apiInterface.getCompany()
    }
}