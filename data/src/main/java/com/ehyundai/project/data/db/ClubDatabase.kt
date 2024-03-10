package com.ehyundai.project.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ehyundai.project.data.model.club.ClubEntity

/**
 * Room DB
 * DB 의 entities 는 ClubEntity 로 설정.
 */
@Database(entities = [ClubEntity::class], version = 1, exportSchema = false)
abstract class ClubDatabase : RoomDatabase() {
    abstract fun clubDao(): ClubDao
}