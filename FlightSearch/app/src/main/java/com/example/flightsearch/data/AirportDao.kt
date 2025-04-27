package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {

    @Query(
        """
        SELECT *
        FROM airport
        WHERE iata_code LIKE :keyword || '%'
           OR name LIKE '%' || :keyword || '%'
        ORDER BY CASE
                     WHEN iata_code LIKE :keyword || '%' THEN 0
                     ELSE 1
                     END,
                 name
        LIMIT :limit
        """
    )
    suspend fun getAutocompleteAirPorts(
        keyword: String,
        limit: Int,
    ): List<Airport>

    @Query(
        """
        select *
        from airport
        where iata_code = :code
        """
    )
    fun getAirportByCode(code: String): Flow<List<Airport>>
}