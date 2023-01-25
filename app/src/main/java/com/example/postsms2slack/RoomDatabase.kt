package com.example.postsms2slack

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [slack_channel::class], version = 1)
abstract class SlackRoomDatabase : RoomDatabase() {
    abstract fun slackDAO(): slackDao

    companion object {
        @Volatile
        private var INSTANCE: SlackRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): SlackRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SlackRoomDatabase::class.java,
                    "slack_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(SlackDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }

        private class SlackDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

        }
    }
}