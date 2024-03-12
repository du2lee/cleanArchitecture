package com.ehyundai.project.plays.hilt

import android.content.Context
import androidx.room.Room
import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.db.company.CompanyDao
import com.ehyundai.project.data.db.company.CompanyDatabase
import com.ehyundai.project.data.repository.company.CompanyRepositoryImpl
import com.ehyundai.project.data.repository.company.local.CompanyLocalDataSource
import com.ehyundai.project.data.repository.company.local.CompanyLocalDataSourceImpl
import com.ehyundai.project.data.repository.company.remote.CompanyRemoteDataSource
import com.ehyundai.project.data.repository.company.remote.CompanyRemoteDataSourceImpl
import com.ehyundai.project.domain.repository.CompanyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CompanyDataModule {

    @Singleton
    @Provides
    fun provideCompanyRepository(
        companyLocalDataSource: CompanyLocalDataSource,
        companyRemoteDataSource: CompanyRemoteDataSource
    ): CompanyRepository {
        return CompanyRepositoryImpl(companyLocalDataSource, companyRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiInterface: ApiInterface): CompanyRemoteDataSource {
        return CompanyRemoteDataSourceImpl(apiInterface)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(companyDao: CompanyDao): CompanyLocalDataSource {
        return CompanyLocalDataSourceImpl(companyDao)
    }

    @Singleton
    @Provides
    fun provideCompanyDao(companyDatabase: CompanyDatabase): CompanyDao {
        return companyDatabase.companyDao()
    }

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): CompanyDatabase {
        return Room.databaseBuilder(
            context,
            CompanyDatabase::class.java,
            "Company.db"
        ).build()
    }
}