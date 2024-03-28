package com.ehyundai.project.data.repository.post.remote

import com.ehyundai.project.data.model.board.BoardResponse
import io.reactivex.Single

interface PostRemoteDataSource {
    fun getPost(token: String, clubNo: String): Single<BoardResponse>
}