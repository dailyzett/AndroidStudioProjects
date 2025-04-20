package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getData(): List<AmphibiansModel>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getData(): List<AmphibiansModel> {
        return amphibiansApiService.getAmphibians()
    }
}