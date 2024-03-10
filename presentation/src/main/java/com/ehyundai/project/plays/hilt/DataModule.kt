package com.ehyundai.project.plays.hilt

import android.content.Context
import androidx.room.Room
import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.db.ClubDao
import com.ehyundai.project.data.db.ClubDatabase
import com.ehyundai.project.data.repository.search.ClubRepositoryImpl
import com.ehyundai.project.data.repository.search.TestRepositoryImpl
import com.ehyundai.project.data.repository.search.local.ClubLocalDataSource
import com.ehyundai.project.data.repository.search.local.ClubLocalDataSourceImpl
import com.ehyundai.project.data.repository.search.remote.ClubRemoteDataSource
import com.ehyundai.project.data.repository.search.remote.ClubRemoteDataSourceImpl
import com.ehyundai.project.domain.repository.ClubRepository
import com.ehyundai.project.domain.repository.TestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideTestRepository(): TestRepository {
        return TestRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideClubRepository(
        clubRemoteDataSource: ClubRemoteDataSource,
        clubLocalDataSource: ClubLocalDataSource,
    ): ClubRepository {
        return ClubRepositoryImpl(clubLocalDataSource, clubRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiInterface: ApiInterface): ClubRemoteDataSource {
        return ClubRemoteDataSourceImpl(apiInterface)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(clubDao: ClubDao): ClubLocalDataSource {
        return ClubLocalDataSourceImpl(clubDao)
    }

    @Singleton
    @Provides
    fun provideClubDao(clubDatabase: ClubDatabase): ClubDao {
        return clubDatabase.clubDao()
    }

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): ClubDatabase {
        return Room.databaseBuilder(
            context,
            ClubDatabase::class.java,
            "Club.db"
        ).build()
    }
//
//
//    @Singleton
//    @Provides
//    fun provideKtorInterface(client: HttpClient): KtorInterface {
//        return KtorInterfaceImpl(client)
//    }
}