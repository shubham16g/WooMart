package com.shubhamgupta16.woomart.offlinecart

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CartItemEntity::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartItemDao(): CartItemDao
}