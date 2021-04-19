package com.shaadi.project.sample.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.shaadi.project.sample.data.local.db.entity.DummyEntity
import io.reactivex.Single


@Dao
interface DummyDao {

    @Query("SELECT * FROM dummy_entity")
    fun getAll(): Single<List<DummyEntity>>

    @Insert
    fun insert(entity: DummyEntity)

    @Delete
    fun delete(entity: DummyEntity)
}