package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    fun getFavorites(): Flow<List<Favorite>>

    @Query(
        """
        DELETE FROM favorite
        WHERE departure_code = :departureCode
        """
    )
    suspend fun deleteFavoriteByDepartureCode(departureCode: String)
}