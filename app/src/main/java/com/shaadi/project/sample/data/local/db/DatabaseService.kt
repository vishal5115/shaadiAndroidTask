package com.shaadi.project.sample.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shaadi.project.sample.data.local.db.dao.DummyDao
import com.shaadi.project.sample.data.local.db.entity.DummyEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        DummyEntity::class
    ],
    exportSchema = false,
    version = 2
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun dummyDao(): DummyDao
}