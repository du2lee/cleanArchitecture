package com.ehyundai.project.data.repository.post

import android.util.Log
import com.ehyundai.project.data.mapper.mapperToPost
import com.ehyundai.project.data.repository.post.remote.PostRemoteDataSource
import com.ehyundai.project.domain.model.Post
import com.ehyundai.project.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource
) : PostRepository {

    override fun getPost(token: String, clubNo: String): Single<Post> {
        return postRemoteDataSource.getPost(token, clubNo).flatMap {
            Log.i("duhui", it.toString())
            Single.just(mapperToPost(it))
        }
    }
}