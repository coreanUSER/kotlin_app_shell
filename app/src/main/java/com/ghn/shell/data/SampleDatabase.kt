package com.ghn.shell.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Sample::class], version = 1, exportSchema = false)
abstract class SampleDatabase : RoomDatabase() {

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: SampleDatabase? = null

        fun getInstance(context: Context): SampleDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): SampleDatabase {
            return Room.databaseBuilder(context, SampleDatabase::class.java, "SAMPLE_DB")
                .addCallback(
                    // addCallback : add default data to our database
                    object : RoomDatabase.Callback() {
                        // onCreate : when the database is created for the first time, after the tables have been created
                        // onOpen : when the database was opened
                        //
                        // Since the DAOs can only be accessed once these methods return,
                        // we‘re creating a new thread where we’re getting a reference to the database,
                        // get the DAO, and then insert the data.
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }
                    }
                ).build()
        }
    }
}