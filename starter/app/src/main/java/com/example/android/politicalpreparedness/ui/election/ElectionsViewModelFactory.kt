package com.example.android.politicalpreparedness.ui.election

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.data.AppDataSource
import com.example.android.politicalpreparedness.data.AppRepository
import com.example.android.politicalpreparedness.data.database.ElectionDatabase

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the key for the night and the SleepDatabaseDao to the ViewModel.
 */
//TODO: Create Factory to generate ElectionViewModel with provided election datasource
class ElectionsViewModelFactory (val app: Application,
                                 val dataSource: AppRepository
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ElectionsViewModel::class.java)) {
            return ElectionsViewModel(app, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}