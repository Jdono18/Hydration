package com.example.hydration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WaterRecord::class], version = 1)
abstract class WaterDatabase: RoomDatabase() {  // WaterDatabase inherits from RoomDatabase()

    abstract fun waterDao(): WaterDao

    companion object {

        // singleton pattern
        @Volatile // makes sure only one thread in program is accessing variables at one time
        private var INSTANCE: WaterDatabase? = null  // initializes variable INSTANCE that is a WaterDatabase type.  Using Elvis operator returns null

        fun getDatabase(context: Context): WaterDatabase { // defines getDatabase function (context parameter) returns WaterDatabase object
            return INSTANCE ?: synchronized(this) {  // return INSTANCE variable.  Synchronized means only one part of the program can one the code at once.  2 copies will not be created
                val instance = Room.databaseBuilder(  // initializes instance value that holds the Waterdatabase object
                    context, WaterDatabase::class.java, "water_database") // takes the listed parameters
                    .build()  // calls build function.  Builds database.
                INSTANCE = instance // var INSTANCE set to new instance created above
                return instance // returns instance value
            }
        }
    }
}