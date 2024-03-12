package com.ehyundai.project.data.repository.company.local

import com.ehyundai.project.data.db.company.CompanyDao
import com.ehyundai.project.data.model.company.CompanyEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CompanyLocalDataSourceImpl @Inject constructor(private val companyDao: CompanyDao) : CompanyLocalDataSource {
    override fun insertCompany(company: List<CompanyEntity>): Completable = companyDao.insertCompany(company)

    override fun getCompany(): Single<List<CompanyEntity>> = companyDao.getCompany()

    override fun deleteCompany(): Completable = companyDao.deleteCompany()
}