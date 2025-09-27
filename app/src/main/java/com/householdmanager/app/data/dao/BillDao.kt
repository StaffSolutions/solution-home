package com.householdmanager.app.data.dao

import androidx.room.*
import com.householdmanager.app.data.entity.Bill
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface BillDao {
    @Query("SELECT * FROM bills ORDER BY dueDate ASC")
    fun getAllBills(): Flow<List<Bill>>
    
    @Query("SELECT * FROM bills WHERE id = :billId")
    suspend fun getBillById(billId: String): Bill?
    
    @Query("SELECT * FROM bills WHERE isPaid = 0 ORDER BY dueDate ASC")
    fun getUnpaidBills(): Flow<List<Bill>>
    
    @Query("SELECT * FROM bills WHERE isPaid = 1 ORDER BY paidDate DESC")
    fun getPaidBills(): Flow<List<Bill>>
    
    @Query("SELECT * FROM bills WHERE dueDate BETWEEN :startDate AND :endDate")
    fun getBillsByDateRange(startDate: Date, endDate: Date): Flow<List<Bill>>
    
    @Query("SELECT * FROM bills WHERE isRecurring = 1")
    fun getRecurringBills(): Flow<List<Bill>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBill(bill: Bill)
    
    @Update
    suspend fun updateBill(bill: Bill)
    
    @Delete
    suspend fun deleteBill(bill: Bill)
    
    @Query("UPDATE bills SET isPaid = 1, paidDate = :paidDate WHERE id = :billId")
    suspend fun markBillAsPaid(billId: String, paidDate: Date)
}