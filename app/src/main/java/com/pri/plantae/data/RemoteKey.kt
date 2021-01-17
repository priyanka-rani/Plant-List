package com.pri.plantae.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(@PrimaryKey val label: String = "plants", val nextPage: Int?)