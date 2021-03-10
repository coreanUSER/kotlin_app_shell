package com.ghn.shell

import android.app.Application
import android.app.ApplicationErrorReport
import androidx.multidex.MultiDexApplication
import com.ghn.shell.data.SampleDatabase
import com.ghn.shell.data.SampleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SampleApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { SampleDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { SampleRepository(database.sampleDao()) }
}