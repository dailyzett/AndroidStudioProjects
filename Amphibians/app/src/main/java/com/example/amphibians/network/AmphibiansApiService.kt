package com.example.amphibians.network

import com.example.amphibians.data.AmphibiansModel
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("/amphibians")
    suspend fun getAmphibians(): List<AmphibiansModel>
}