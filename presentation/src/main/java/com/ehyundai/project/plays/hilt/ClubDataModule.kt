package com.ehyundai.project.plays.hilt

import android.content.Context
import androidx.room.Room
import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.db.club.ClubDao
import com.ehyundai.project.data.db.club.ClubDatabase
import com.ehyundai.project.data.repository.club.ClubRepositoryImpl
import com.ehyundai.project.data.repository.club.local.ClubLocalDataSource
import com.ehyundai.project.data.repository.club.local.ClubLocalDataSourceImpl
import com.ehyundai.project.data.repository.club.remote.ClubRemoteDataSource
import com.ehyundai.project.data.repository.club.remote.ClubRemoteDataSourceImpl
import com.ehyundai.project.domain.repository.ClubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClubDataModule {

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