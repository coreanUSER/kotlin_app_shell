package com.ghn.shell.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

// https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#4785
@Dao
interface SampleDao {
    
    @Transaction
    suspend fun updateDataAll(samples: List<Sample>) {
        deleteAll()
        insertAll(samples)
    }

    @Query("SELECT * FROM samples ORDER BY name")
    fun getSamples(): Flow<List<Sample>>

    @Query("SELECT * FROM samples WHERE id = :_id")
    fun getSample(_id: String): Flow<Sample>

    // REPLACE : replace the old data and continue the transaction.
    // IGNORE : ignore the conflict. (Continue)
    // ABORT : abort the transaction. (Break)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(samples: List<Sample>)

    @Query("DELETE FROM samples")
    fun deleteAll()
}