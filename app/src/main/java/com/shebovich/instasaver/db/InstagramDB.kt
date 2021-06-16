package com.shebovich.instasaver.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.shebovich.instasaver.models.Profile


@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class InstagramDB : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}