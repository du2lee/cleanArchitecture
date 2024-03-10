package com.ehyundai.project.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ehyundai.project.data.model.club.ClubEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ClubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClub(clubs: List<ClubEntity>): Completable

    @Query("SELECT * FROM club")
    fun getAllClubs(): Single<List<ClubEntity>>

    @Query("SELECT * FROM club WHERE clubNo LIKE '%' || :clubNo || '%'")
    fun getClubsByClubNo(clubNo: String): Single<List<ClubEntity>>

    @Query("DELETE FROM club")
    fun deleteAllClubs(): Completable
}