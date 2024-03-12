package com.ehyundai.project.data.db.company

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ehyundai.project.data.model.company.CompanyEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompany(clubs: List<CompanyEntity>): Completable

    @Query("SELECT * FROM company")
    fun getCompany(): Single<List<CompanyEntity>>

    @Query("DELETE FROM company")
    fun deleteCompany(): Completable
}