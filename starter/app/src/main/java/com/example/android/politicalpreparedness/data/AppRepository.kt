package com.example.android.politicalpreparedness.data

import com.example.android.politicalpreparedness.data.database.ElectionDao
import kotlinx.coroutines.*

class AppRepository(private val electionDao: ElectionDao,
                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): AppDataSource {

    override suspend fun getingDataFromApi() {
        println("hola")
    }

}
