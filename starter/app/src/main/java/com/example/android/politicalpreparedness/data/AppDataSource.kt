package com.example.android.politicalpreparedness.data

import com.example.android.politicalpreparedness.data.data_objects.dto.ElectionResponse

interface AppDataSource {

    suspend fun getNextElectionsFromAPIService(): Boolean
}