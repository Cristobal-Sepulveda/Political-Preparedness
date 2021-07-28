package com.example.android.politicalpreparedness.ui.representative

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.base.BaseViewModel
import com.example.android.politicalpreparedness.data.AppDataSource
import com.example.android.politicalpreparedness.data.network.CivicApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepresentativeViewModel(val app: Application, val dataSource: AppDataSource) : BaseViewModel(app) {

    //TODO: Establish live data for representatives and address

    //TODO: Create function to fetch representatives from API from a provided address

    /**
     *  The following code will prove helpful in constructing a representative from the API.
     *  This code combines the two nodes of the RepresentativeResponse into a single official :

    val (offices, officials) = getRepresentativesDeferred.await()
    _representatives.value = offices.flatMap { office -> office.getRepresentatives(officials) }

    Note: getRepresentatives in the above code represents the method used to fetch data from the API
    Note: _representatives in the above code represents the established mutable live data housing representatives

     */
/*    init{
        viewModelScope.launch{
            try{
                val representativesApiResponse = CivicApi.RETROFIT_SERVICE_CIVIC_API
                    .getRepresentatives("AIzaSyBNGDAG_qIAaCtZGcDftVXgI75-CwcWg64",
                    )
            }catch(e: Exception){
                Log.i("ERROR", "ERROR: $e")
            }
    }*/
    //TODO: Create function get address from geo location

    //TODO: Create function to get address from individual fields

}
