package com.example.testtodo.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class UserDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO;

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null;

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE;
            if (tempInstance != null) {
                return tempInstance;
            };
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build();
                INSTANCE = instance;
                return instance;
            };
        };
    }


}