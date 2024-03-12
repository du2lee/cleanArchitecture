package com.ehyundai.project.data.repository.company

import com.ehyundai.project.data.mapper.mapperToCompany
import com.ehyundai.project.data.repository.company.local.CompanyLocalDataSource
import com.ehyundai.project.data.repository.company.remote.CompanyRemoteDataSource
import com.ehyundai.project.domain.model.Company
import com.ehyundai.project.domain.repository.CompanyRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val companyLocalDataSource: CompanyLocalDataSource,
    private val companyRemoteDataSource: CompanyRemoteDataSource
) : CompanyRepository {
    override fun getCompany(): Flowable<List<Company>> {
        return companyLocalDataSource.getCompany()
            .onErrorReturn { listOf() }
            .flatMapPublisher { localCompanys ->
                if(localCompanys.isEmpty()){
                    getRemoteCompany()
                        .toFlowable()
                        .onErrorReturn { listOf() }
                } else{
                    val local = Single.just(mapperToCompany(localCompanys))
                    val remote = getRemoteCompany()
                        .onErrorResumeNext { local }
                    Single.concat(local, remote)
                }
            }
    }

    override fun getRemoteCompany(): Single<List<Company>> {
        return companyRemoteDataSource.getCompany()
            .flatMap {
                companyLocalDataSource
                    .insertCompany(it.data)
                    .andThen(Single.just(mapperToCompany(it.data)))
            }
    }
}