package com.ehyundai.project.domain.usecase

import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.domain.repository.TestRepository
import io.reactivex.Flowable
import javax.inject.Inject

class TestUseCase @Inject constructor(private val repository: TestRepository) {
    operator fun invoke(): Flowable<ArrayList<Club>> = repository.getClubs()
}