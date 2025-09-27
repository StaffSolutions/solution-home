package com.householdmanager.app.di

import android.content.Context
import androidx.room.Room
import com.householdmanager.app.data.database.HouseholdDatabase
import com.householdmanager.app.data.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideHouseholdDatabase(@ApplicationContext context: Context): HouseholdDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HouseholdDatabase::class.java,
            "household_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: HouseholdDatabase): UserDao = database.userDao()

    @Provides
    fun provideHouseDao(database: HouseholdDatabase): HouseDao = database.houseDao()

    @Provides
    fun provideHouseUserDao(database: HouseholdDatabase): HouseUserDao = database.houseUserDao()

    @Provides
    fun provideBillDao(database: HouseholdDatabase): BillDao = database.billDao()

    @Provides
    fun provideTaskDao(database: HouseholdDatabase): TaskDao = database.taskDao()

    @Provides
    fun provideGroceryItemDao(database: HouseholdDatabase): GroceryItemDao = database.groceryItemDao()

    @Provides
    fun provideMealPlanDao(database: HouseholdDatabase): MealPlanDao = database.mealPlanDao()
}