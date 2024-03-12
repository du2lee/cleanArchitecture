package com.ehyundai.project.data.repository.company.local

import com.ehyundai.project.data.model.company.CompanyEntity
import io.reactivex.Completable
import io.reactivex.Single

interface CompanyLocalDataSource {

    fun insertCompany(company: List<CompanyEntity>): Completable

    fun getCompany(): Single<List<CompanyEntity>>

    fun deleteCompany(): Completable
}