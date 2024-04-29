package com.ehyundai.project.data.repository.post.remote

import com.ehyundai.project.data.model.board.BoardResponse
import io.reactivex.Single

interface PostRemoteDataSource {
    fun getPost(token: String, clubNo: String): Single<BoardResponse>

    fun getPost(token: String): Single<BoardResponse> // 본인 동호회 게시글 조회
}