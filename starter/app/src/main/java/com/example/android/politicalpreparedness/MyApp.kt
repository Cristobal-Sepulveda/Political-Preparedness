package com.example.android.politicalpreparedness

import android.app.Application
import com.example.android.politicalpreparedness.base.BaseViewModel
import com.example.android.politicalpreparedness.data.AppDataSource
import com.example.android.politicalpreparedness.data.AppRepository
import com.example.android.politicalpreparedness.data.database.getDatabase
import com.example.android.politicalpreparedness.ui.election.ElectionsViewModel
import com.example.android.politicalpreparedness.ui.representative.RepresentativeViewModel
import com.example.android.politicalpreparedness.ui.voter_info.VoterInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

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
                    get() as AppRepository
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
            single{getDatabase(this@MyApp).electionDao}

            //REPOSITORY
            single{ AppRepository(get()) as AppDataSource }
            //REPOSITORY
            single{ AppRepository(get())}
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }

}
