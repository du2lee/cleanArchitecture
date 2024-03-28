package com.ehyundai.project.plays.hilt

import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.repository.post.PostRepositoryImpl
import com.ehyundai.project.data.repository.post.remote.PostRemoteDataSource
import com.ehyundai.project.data.repository.post.remote.PostRemoteDataSourceImpl
import com.ehyundai.project.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostDataModule {

    @Singleton
    @Provides
    fun providePostRepository(
        postRemoteDataSource: PostRemoteDataSource
    ): PostRepository {
        return PostRepositoryImpl(postRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiInterface: ApiInterface): PostRemoteDataSource {
        return PostRemoteDataSourceImpl(apiInterface)
    }
}