package com.ehyundai.project.domain.usecase

import com.ehyundai.project.domain.repository.LoginRepository
import javax.inject.Inject

class SearchLoginUseCase @Inject constructor(private val repository: LoginRepository) {
}