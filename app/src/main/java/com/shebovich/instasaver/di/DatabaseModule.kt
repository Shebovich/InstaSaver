package com.shebovich.instasaver.di

import android.app.Application
import androidx.room.Room
import com.shebovich.instasaver.db.InstagramDB
import com.shebovich.instasaver.db.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providePokemonDB(application: Application): InstagramDB {
        return Room.databaseBuilder(application, InstagramDB::class.java, "InstagramDB")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePokeDao(instagramDB: InstagramDB): ProfileDao {
        return instagramDB.profileDao()
    }
}