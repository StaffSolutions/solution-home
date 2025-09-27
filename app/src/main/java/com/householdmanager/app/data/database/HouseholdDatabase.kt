package com.householdmanager.app.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.householdmanager.app.data.converters.Converters
import com.householdmanager.app.data.dao.*
import com.householdmanager.app.data.entity.*

@Database(
    entities = [
        User::class,
        House::class,
        HouseUser::class,
        Bill::class,
        Task::class,
        GroceryItem::class,
        MealPlan::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HouseholdDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun houseDao(): HouseDao
    abstract fun houseUserDao(): HouseUserDao
    abstract fun billDao(): BillDao
    abstract fun taskDao(): TaskDao
    abstract fun groceryItemDao(): GroceryItemDao
    abstract fun mealPlanDao(): MealPlanDao

    companion object {
        @Volatile
        private var INSTANCE: HouseholdDatabase? = null

        fun getDatabase(context: Context): HouseholdDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HouseholdDatabase::class.java,
                    "household_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}