package com.ehyundai.project.domain.usecase

import com.ehyundai.project.domain.repository.PostRepository
import javax.inject.Inject

class GetBoardUseCase @Inject constructor(private val repository: PostRepository) {
    fun getPost(token: String, clubNo: String) = repository.getPost(token, clubNo)
}