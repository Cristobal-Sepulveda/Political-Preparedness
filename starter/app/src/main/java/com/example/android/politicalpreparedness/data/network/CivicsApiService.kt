package com.example.android.politicalpreparedness.data.network

import com.example.android.politicalpreparedness.data.network.jsonadapter.ElectionAdapter
import com.example.android.politicalpreparedness.data.network.jsonadapter.RepresentativeAdapter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.*

private const val BASE_URL = "https://www.googleapis.com/civicinfo/v2/"

// TODO: Add adapters for Java Date and custom adapter ElectionAdapter (included in project)
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(ElectionAdapter())
        .add(RepresentativeAdapter())
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

/**
 *  Documentation for the Google Civics API Service can be found at
 *  https://developers.google.com/civic-information/docs/v2
 */
interface CivicApiService {

    //TODO: Add elections API Call
    @GET("elections")
    suspend fun getElections()

    //TODO: Add voterinfo API Call
    @GET("voterinfo")
    suspend fun getVoterInfo()

    //TODO: Add representatives API Call
    @GET("representatives")
    suspend fun getRepresentatives()
}

object CivicApi {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(CivicsHttpClient.getClient())
        .baseUrl(BASE_URL)
        .build()

    val RETROFIT_SERVICE_CIVIC_API: CivicApiService by lazy {
        retrofit.create(CivicApiService::class.java)
    }
}