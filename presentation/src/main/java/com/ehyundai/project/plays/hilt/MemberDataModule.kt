package com.ehyundai.project.plays.hilt

import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.repository.member.MemberRepositoryImpl
import com.ehyundai.project.data.repository.member.remote.MemberRemoteDataSource
import com.ehyundai.project.data.repository.member.remote.MemberRemoteDataSourceImpl
import com.ehyundai.project.domain.repository.MemberRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MemberDataModule {

    @Singleton
    @Provides
    fun provideMemberRepository(
        memberRemoteDataSource: MemberRemoteDataSource
    ): MemberRepository {
        return MemberRepositoryImpl(memberRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiInterface: ApiInterface): MemberRemoteDataSource {
        return MemberRemoteDataSourceImpl(apiInterface)
    }
}