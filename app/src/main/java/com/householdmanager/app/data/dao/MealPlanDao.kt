package com.householdmanager.app.data.dao

import androidx.room.*
import com.householdmanager.app.data.entity.MealPlan
import com.householdmanager.app.data.entity.MealType
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface MealPlanDao {
    @Query("SELECT * FROM meal_plans ORDER BY date DESC, mealType ASC")
    fun getAllMealPlans(): Flow<List<MealPlan>>
    
    @Query("SELECT * FROM meal_plans WHERE id = :mealPlanId")
    suspend fun getMealPlanById(mealPlanId: String): MealPlan?
    
    @Query("SELECT * FROM meal_plans WHERE date = :date ORDER BY mealType ASC")
    fun getMealPlansByDate(date: Date): Flow<List<MealPlan>>
    
    @Query("SELECT * FROM meal_plans WHERE date BETWEEN :startDate AND :endDate ORDER BY date ASC, mealType ASC")
    fun getMealPlansByDateRange(startDate: Date, endDate: Date): Flow<List<MealPlan>>
    
    @Query("SELECT * FROM meal_plans WHERE mealType = :mealType ORDER BY date DESC")
    fun getMealPlansByType(mealType: MealType): Flow<List<MealPlan>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealPlan(mealPlan: MealPlan)
    
    @Update
    suspend fun updateMealPlan(mealPlan: MealPlan)
    
    @Delete
    suspend fun deleteMealPlan(mealPlan: MealPlan)
}