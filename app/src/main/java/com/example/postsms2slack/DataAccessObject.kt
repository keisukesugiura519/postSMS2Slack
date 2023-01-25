package com.example.postsms2slack

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface slackDao {
    @Query("SELECT * FROM slack_channelTable ORDER BY channelID ASC")
    fun getListSlackChannel(): Flow<List<slack_channel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(slackChannel: slack_channel)

    @Query("DELETE FROM slack_channelTable")
    suspend fun deleteAll()
}