package com.example.android.politicalpreparedness.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.politicalpreparedness.data.network.models.Election

@Database(entities = [Election::class], version = 1, exportSchema = false)
abstract class LOCAL_DATABASE: RoomDatabase() {
    abstract val electionDao: ElectionDao
}

private lateinit var INSTANCE: LOCAL_DATABASE

fun getDatabase(context: Context): LOCAL_DATABASE {
    synchronized(LOCAL_DATABASE::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                LOCAL_DATABASE::class.java,
                "database").build()
        }
    }
    return INSTANCE
}
