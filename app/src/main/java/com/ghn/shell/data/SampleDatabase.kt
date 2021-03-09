package com.ghn.shell.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Sample::class], version = 1, exportSchema = false)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun sampleDao(): SampleDao

    private class SampleDatabaseCallBack(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        // onCreate : when the database is created for the first time, after the tables have been created
        // onOpen : when the database was opened
        //
        // Since the DAOs can only be accessed once these methods return,
        // we‘re creating a new thread where we’re getting a reference to the database,
        // get the DAO, and then insert the data.
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var sampleDao = database.sampleDao()

                    // initialized
                    // sampleDao.deleteAll()
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: SampleDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): SampleDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SampleDatabase::class.java,
                    "sample_database"
                )
                    // addCallback : add default data to our database
                    .addCallback(SampleDatabaseCallBack(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}