package com.example.hydration

import android.app.Application

class HydrationApplication: Application() {  // subclass of android application to override Androids application class to pass in our database

    private val database by lazy { WaterDatabase.getDatabase(this) } // initializes value database to store the database

    // activity can access this repository
    val repository by lazy { WaterRepository(database.waterDao())} // initializes value repository that passes in the dataAccessObject to be able to call it's functions to WaterRepository
}