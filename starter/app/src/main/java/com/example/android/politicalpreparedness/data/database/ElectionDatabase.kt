package com.example.android.politicalpreparedness.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO

@Database(entities = [ELECTION_DBO::class], version = 1, exportSchema = false)
abstract class ElectionDatabase: RoomDatabase() {
    abstract val electionDao: ElectionDao
}

private lateinit var INSTANCE: ElectionDatabase

fun getDatabase(context: Context): ElectionDatabase {
    synchronized(ElectionDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ElectionDatabase::class.java,
                "database").build()
        }
    }
    return INSTANCE
}
