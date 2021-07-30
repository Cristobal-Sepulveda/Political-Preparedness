package com.example.android.politicalpreparedness.ui.representative

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.graphics.blue
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.android.politicalpreparedness.base.BaseFragment
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.databinding.FragmentRepresentativeBinding
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ADDRESS_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.util.RepresentativeListAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import java.util.Locale
import java.util.concurrent.TimeUnit

class DetailFragment : BaseFragment() {


    //TODO: Add Constant for Location request
    companion object {
        const val REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE = 33
        const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
        const val LOCATION_PERMISSION_INDEX = 0
        const val BACKGROUND_LOCATION_PERMISSION_INDEX = 1
        var locationPermissionGranted = false
        val GEOFENCE_EXPIRATION_IN_MILLISECONDS: Long = TimeUnit.HOURS.toMillis(1)
    }
    var editLine1 = ""
    var editLine2 = ""
    var editCity = ""
    var editZip = ""

    // The entry point to the Fused Location Provider.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var lastKnownLocation: Location? = null
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>

    private val runningQOrLater = Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.Q

    //TODO: Add ViewModel values and create ViewModel
    /*viewModel = ViewModelProvider(this).get(RepresentativeViewModel::class.java)*/
    //I prefer to use KOIN to inject things. Its good practice to make great test' later
    override val _viewModel: RepresentativeViewModel by inject()

    //TODO: Establish bindings
    private lateinit var binding: FragmentRepresentativeBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val states = resources.getStringArray(R.array.states)

        val spinnerAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item,
            states)

        //TODO: Establish bindings
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_representative, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = _viewModel
        binding.stateSpinner.adapter = spinnerAdapter
        binding.address = ADDRESS_DOMAIN_OBJECT("", "", "", "", "")

        //TODO: Define and assign Representative adapter
        //TODO: Populate Representative adapter
        binding.representativesFromApiRecyclerView.adapter = RepresentativeListAdapter(
            RepresentativeListAdapter.OnClickListener{

            }
        )

        //TODO: Establish button listeners for field and location search
        binding.useMyLocationButton.setOnClickListener {
            val aux = _viewModel.geoCodeLocation(lastKnownLocation!!)
            binding.address = aux
            val state = aux.state
            if(state != ""){
                binding.stateSpinner.setSelection(spinnerAdapter.getPosition(state))
            }
        }

        // Construct a FusedLocationProviderClient.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        checkPermissionsAndGetDeviceLocation()

        /**
         * ALL OF THIS IS FOR WATCH THE EDIT TEXT's CHANGES AND CHECK IF THE FIND MY REPRESENTATIVE
         * BUTTON SHOULD BE ENABLED or not
         * */
        binding.addressLine1EditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }

            override fun afterTextChanged(s: Editable?) {
                editLine1 = s.toString()
                _viewModel.isRepresentativeButtonEnabled(
                    editLine1,
                    editLine2,
                    editCity,
                    editZip
                )
            }
        })

        binding.addressLine2EditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }

            override fun afterTextChanged(s: Editable?) {
                editLine2 = s.toString()
                _viewModel.isRepresentativeButtonEnabled(
                    editLine1,
                    editLine2,
                    editCity,
                    editZip
                )
            }
        })

        binding.cityEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }

            override fun afterTextChanged(s: Editable?) {
                editCity = s.toString()
                _viewModel.isRepresentativeButtonEnabled(
                    editLine1,
                    editLine2,
                    editCity,
                    editZip
                )
            }
        })

        binding.zipEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }

            override fun afterTextChanged(s: Editable?) {
                editZip = s.toString()
                _viewModel.isRepresentativeButtonEnabled(
                    editLine1,
                    editLine2,
                    editCity,
                    editZip
                )
            }
        })
        /**
         * ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
         * ALL OF THIS ABOVE FOR WATCH THE EDIT TEXT's CHANGES AND CHECK IF THE FIND MY REPRESENTATIVE
         * BUTTON SHOULD BE ENABLED or not
         * ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
         * */

        return binding.root
    }

    private fun checkPermissionsAndGetDeviceLocation() {
        if (foregroundAndBackgroundLocationPermissionApproved()) {
            locationPermissionGranted = true
            getDeviceLocation()
        } else {
            requestForegroundAndBackgroundLocationPermissions()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (
            grantResults.isEmpty() ||
            grantResults[LOCATION_PERMISSION_INDEX] == PackageManager.PERMISSION_DENIED ||
            (requestCode == REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE &&
                    grantResults[BACKGROUND_LOCATION_PERMISSION_INDEX] ==
                    PackageManager.PERMISSION_DENIED)) {
            Snackbar.make(
                binding.root,
                R.string.permission_denied_explanation,
                Snackbar.LENGTH_LONG
            )
                .setAction(R.string.settings) {
                    startActivity(Intent().apply {
                        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        data = Uri.fromParts("package",
                            "com.example.android.politicalpreparedness",
                            null)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                }.show()
        }else{
            val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
            if (Build.VERSION.SDK_INT >= 26) {
                ft.setReorderingAllowed(false)
            }
            ft.detach(this).attach(this).commit()
        }
    }

    /** Get the best and most recent location of the device, which may be null in rare
     * cases when a location is not available.*/
    fun getDeviceLocation(){
        try {
            if (locationPermissionGranted) {
                Log.i("cristobal", "$locationPermissionGranted")
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        _viewModel.showSnackBar.value = "Permission available"
                        lastKnownLocation = task.result
                        if(lastKnownLocation != null){
                            Log.i("getDeviceLocation","device location was save correctly")
                            Log.i("getDeviceLocation", "$lastKnownLocation")
                        }else{
                            lastKnownLocation = Location(
                                "fused 37.421908,-122.084108 hAcc=603 et=+3d1h41m54s192ms" +
                                        " vel=0.0 bear=90.0 vAcc=??? sAcc=??? bAcc=???")
                            Log.i("getDeviceLocation", "result was null, " +
                                    "setting lastKnownLocation as :$lastKnownLocation")
                        }
                    } else {
                        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
                        if (Build.VERSION.SDK_INT >= 26) {
                            ft.setReorderingAllowed(false)
                        }
                        ft.detach(this).attach(this).commit()
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    @TargetApi(29)
    private fun requestForegroundAndBackgroundLocationPermissions() {
        if (foregroundAndBackgroundLocationPermissionApproved())
            return
        var permissionsArray = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        val resultCode = when {
            runningQOrLater -> {
                permissionsArray += Manifest.permission.ACCESS_BACKGROUND_LOCATION
                REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE
            }
            else -> REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        }
        requestPermissions(
            permissionsArray,
            resultCode
        )
    }

    @TargetApi(29)
    fun foregroundAndBackgroundLocationPermissionApproved(): Boolean {
        val foregroundLocationApproved = (
                PackageManager.PERMISSION_GRANTED ==
                        ActivityCompat.checkSelfPermission(requireActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION))
        val backgroundPermissionApproved =
            if (runningQOrLater) {
                PackageManager.PERMISSION_GRANTED ==
                        ActivityCompat.checkSelfPermission(
                            requireActivity(), Manifest.permission.ACCESS_BACKGROUND_LOCATION
                        )
            } else {
                true
            }
        return foregroundLocationApproved && backgroundPermissionApproved
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


}