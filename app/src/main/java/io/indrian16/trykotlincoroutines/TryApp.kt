package io.indrian16.trykotlincoroutines

import android.app.Application
import com.github.ajalt.timberkt.Timber
import io.indrian16.trykotlincoroutines.di.appModule
import io.indrian16.trykotlincoroutines.di.netModule
import io.indrian16.trykotlincoroutines.di.repoModule
import org.koin.android.ext.android.startKoin

class TryApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        startKoin(this, arrayListOf(

            netModule,
            repoModule,
            appModule
        ))
    }
}