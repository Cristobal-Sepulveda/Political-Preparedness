package com.example.android.politicalpreparedness

import android.app.Application
import android.app.NotificationManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.work.*
import com.example.android.politicalpreparedness.data.AppDataSource
import com.example.android.politicalpreparedness.data.AppRepository
import com.example.android.onematchproject.data.app_database.getDatabase
import com.example.android.onematchproject.ui.map.MapViewModel
import com.example.android.onematchproject.ui.singleField.SingleFieldViewModel
import com.example.android.onematchproject.utils.updatingCalendar_inAllFields_toNextDay_inCLOUDFIRESTORE
import com.example.android.onematchproject.utils.updatingFIELD_DBO_IN_APP_DATABASE
import com.example.android.politicalpreparedness.ui.election.ElectionsViewModel
import com.example.android.politicalpreparedness.ui.representative.RepresentativeViewModel
import com.example.android.politicalpreparedness.ui.voter_info.VoterInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class MyApp : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        /**
         * using Koin Library as a service locator
         */
        val myModule = module {

            //Declare singleton definitions to be later injected using by inject()
            single {
                ElectionsViewModel(
                    get(),
                    get() as AppDataSource
                )
            }

            single{
                RepresentativeViewModel(
                    get(),
                    get() as AppDataSource
                )
            }

            single{
                VoterInfoViewModel(
                    get(),
                    get() as AppDataSource
                )
            }

            //LOCAL_DATABASE, here im creating the local database in the first start and
            // after that, the db instance persist on the User phone, even if he close the app
            single{getDatabase(this@MyApp).fieldDao}

            //REPOSITORY
            single{ AppRepository(get()) as AppDataSource }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }

        val notificationManager = ContextCompat.getSystemService(
                applicationContext,
                NotificationManager::class.java
        ) as NotificationManager

        delayedInit()

    }

    private fun delayedInit() {
        applicationScope.launch{
            setupRecurringWork_fieldUpdate()
            setupRecurringWork_calendarUpdate()
        }
    }

    private fun setupRecurringWork_fieldUpdate() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<updatingFIELD_DBO_IN_APP_DATABASE>(15, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            updatingFIELD_DBO_IN_APP_DATABASE.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }

    private fun setupRecurringWork_calendarUpdate() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<updatingCalendar_inAllFields_toNextDay_inCLOUDFIRESTORE>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            updatingCalendar_inAllFields_toNextDay_inCLOUDFIRESTORE.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }

}
