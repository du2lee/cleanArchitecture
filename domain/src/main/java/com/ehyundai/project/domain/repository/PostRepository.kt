package com.ehyundai.project.domain.repository

import com.ehyundai.project.domain.model.Post
import io.reactivex.Single

interface PostRepository {
    fun getPost(token: String, clubNo: String): Single<Post>
}