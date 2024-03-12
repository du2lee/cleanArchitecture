package com.ehyundai.project.data.db.company

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ehyundai.project.data.model.company.CompanyEntity

/**
 * Room DB
 * DB 의 entities 는 ClubEntity 로 설정.
 */
@Database(entities = [CompanyEntity::class], version = 1, exportSchema = false)
abstract class CompanyDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao
}