package com.ehyundai.project.domain.usecase

import com.ehyundai.project.domain.repository.LoginRepository
import javax.inject.Inject

class InsertLoginUseCase @Inject constructor(private val repository: LoginRepository)  {
    fun setAutoLogin(flag: Boolean){
        repository.autoLogin = flag
    }
}