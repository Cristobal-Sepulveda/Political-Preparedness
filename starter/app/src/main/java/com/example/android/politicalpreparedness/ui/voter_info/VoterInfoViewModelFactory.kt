package com.example.android.politicalpreparedness.ui.voter_info

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.data.data_objects.domain_object.DIVISION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.database.ElectionDatabase

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the key for the night and the SleepDatabaseDao to the ViewModel.
 */
//TODO: Create Factory to generate VoterInfoViewModel with provided election datasource
class VoterInfoViewModelFactory(
    val election: Int,
    val division: DIVISION_DOMAIN_OBJECT,
    val app: Application,
    val dataBase: ElectionDatabase
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoterInfoViewModel::class.java)) {
            return VoterInfoViewModel(election, division, app, dataBase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}