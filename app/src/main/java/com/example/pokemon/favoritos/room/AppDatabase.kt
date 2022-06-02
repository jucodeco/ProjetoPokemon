package com.example.pokemon.favoritos.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [PokemonFavorito::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getfavoriteDAO(): FavoriteDAO

    companion object {
        private lateinit var INSTANCE: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "guestDB")
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }


        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Favoritos")
            }
        }

    }
}