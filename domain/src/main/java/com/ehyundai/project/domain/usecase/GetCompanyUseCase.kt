package com.ehyundai.project.domain.usecase

import com.ehyundai.project.domain.model.Company
import com.ehyundai.project.domain.repository.CompanyRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetCompanyUseCase @Inject constructor(private val repository: CompanyRepository) {
    operator fun invoke(): Flowable<List<Company>> = repository.getCompany()
}