package com.example.android.politicalpreparedness.data

interface AppDataSource {
    suspend fun gettingAndSavingInDBNextElections_fromAPIService()
}