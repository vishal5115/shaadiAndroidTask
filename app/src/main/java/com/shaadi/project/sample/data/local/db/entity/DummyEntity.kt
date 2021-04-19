package com.shaadi.project.sample.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "dummy_entity")
data class DummyEntity(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Int,

    @ColumnInfo(name = "first")
    @NotNull
    val name: String,

    @ColumnInfo(name = "last")
    @NotNull
    val last: String,

    @ColumnInfo(name = "large")
    @NotNull
    val large: String,

    @ColumnInfo(name = "email")
    @NotNull
    val email: String,

    @ColumnInfo(name = "isAccepted")
    @NotNull
    val isAccepted: Boolean
)