package com.example.postsms2slack

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "slack_channelTable")
data class slack_channel(@ColumnInfo(name = "channelID") val channelID: String, @ColumnInfo(name = "token") val token: String)

