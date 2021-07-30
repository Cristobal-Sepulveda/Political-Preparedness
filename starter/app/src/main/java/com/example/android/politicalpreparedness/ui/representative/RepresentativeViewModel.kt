package com.example.android.politicalpreparedness.ui.representative

import android.app.Application
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.android.politicalpreparedness.base.BaseViewModel
import com.example.android.politicalpreparedness.data.AppDataSource
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ADDRESS_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.dto.Representative
import com.example.android.politicalpreparedness.data.data_objects.dto.representatives
import com.example.android.politicalpreparedness.data.network.CivicApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class RepresentativeViewModel(val app: Application, val dataSource: AppDataSource) : BaseViewModel(app) {

    //TODO: Establish live data for representatives and address
    private val _representatives = MutableLiveData<List<Representative>>()
    val representatives: LiveData<List<Representative>>
        get() = _representatives

    private val _buttonAvailable = MutableLiveData(false)
    val buttonAvailable: LiveData<Boolean>
        get() = _buttonAvailable

    val domainRepresentativesInScreen:
            MediatorLiveData<List<Representative>> = MediatorLiveData()

    fun isRepresentativeButtonEnabled(line1: String, line2: String, city: String, zip: String){
        _buttonAvailable.value = !(line1 == ""||line2 == ""||city == "" ||zip == "")
    }

    //TODO: Create function get address from geo location
    //TODO: Create function to get address from individual fields
    fun geoCodeLocation(location: Location): ADDRESS_DOMAIN_OBJECT {
        val geocoder = Geocoder(app, Locale.getDefault())
        return geocoder.getFromLocation(location.latitude, location.longitude, 1)
            .map { address ->
                ADDRESS_DOMAIN_OBJECT(address.thoroughfare, address.subThoroughfare, address.locality, address.adminArea, address.postalCode)
            }
            .first()
    }

    //TODO: Create function to fetch representatives from API from a provided address
    /**
     *  The following code will prove helpful in constructing a representative from the API.
     *  This code combines the two nodes of the RepresentativeResponse into a single official :

    val (offices, officials) = getRepresentativesDeferred.await()
    _representatives.value = offices.flatMap { office -> office.getRepresentatives(officials) }

    Note: getRepresentatives in the above code represents the method used to fetch data from the API
    Note: _representatives in the above code represents the established mutable live data housing representatives

     */
    fun getRepresentativesFromAPI(addressDomainObject: ADDRESS_DOMAIN_OBJECT){
        viewModelScope.launch {
            try {
                val representativesApiResponse = CivicApi.RETROFIT_SERVICE_CIVIC_API
                    .getRepresentatives(
                        "AIzaSyBNGDAG_qIAaCtZGcDftVXgI75-CwcWg64",
                    addressDomainObject.toFormattedString())
                val representatives = representativesApiResponse.representatives
                _representatives.value = representatives
                domainRepresentativesInScreen.addSource(_representatives){
                    domainRepresentativesInScreen.value = it
                }
            } catch (e: Exception) {
                showSnackBar.value = "The direction doesn't exist in EEUU"
                Log.i("ERROR", "ERROR: $e")
            }
        }
    }
}
