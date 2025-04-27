package com.example.flightsearch.data

class FlightRepository(
    private val airportDao: AirportDao,
    private val favoriteDao: FavoriteDao
) {
    suspend fun getAutocompleteAirPorts(keyword: String, limit: Int) =
        airportDao.getAutocompleteAirPorts(keyword, limit)

    suspend fun insertFavorite(favorite: Favorite) =
        favoriteDao.insertFavorite(favorite)

    fun getFavorites() =
        favoriteDao.getFavorites()

    suspend fun deleteByDepartureCode(departureCode: String) =
        favoriteDao.deleteFavoriteByDepartureCode(departureCode)
}