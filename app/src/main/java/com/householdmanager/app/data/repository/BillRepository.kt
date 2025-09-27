package com.householdmanager.app.data.repository

import com.householdmanager.app.data.dao.BillDao
import com.householdmanager.app.data.entity.Bill
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BillRepository @Inject constructor(
    private val billDao: BillDao
) {
    fun getAllBills(): Flow<List<Bill>> = billDao.getAllBills()
    
    suspend fun getBillById(billId: String): Bill? = billDao.getBillById(billId)
    
    fun getUnpaidBills(): Flow<List<Bill>> = billDao.getUnpaidBills()
    
    fun getPaidBills(): Flow<List<Bill>> = billDao.getPaidBills()
    
    fun getBillsByDateRange(startDate: Date, endDate: Date): Flow<List<Bill>> = 
        billDao.getBillsByDateRange(startDate, endDate)
    
    fun getRecurringBills(): Flow<List<Bill>> = billDao.getRecurringBills()
    
    suspend fun insertBill(bill: Bill) = billDao.insertBill(bill)
    
    suspend fun updateBill(bill: Bill) = billDao.updateBill(bill)
    
    suspend fun deleteBill(bill: Bill) = billDao.deleteBill(bill)
    
    suspend fun markBillAsPaid(billId: String, paidDate: Date = Date()) = 
        billDao.markBillAsPaid(billId, paidDate)
    
    suspend fun createBill(
        name: String,
        amount: java.math.BigDecimal,
        dueDate: Date,
        type: com.householdmanager.app.data.entity.BillType,
        createdBy: String,
        isRecurring: Boolean = false,
        reminderDays: Int = 3
    ): Bill {
        val bill = Bill(
            id = generateBillId(),
            name = name,
            amount = amount,
            type = type,
            dueDate = dueDate,
            isPaid = false,
            reminderDays = reminderDays,
            isRecurring = isRecurring,
            createdBy = createdBy,
            createdAt = Date(),
            updatedAt = Date()
        )
        insertBill(bill)
        return bill
    }
    
    private fun generateBillId(): String {
        return "bill_${System.currentTimeMillis()}_${(1000..9999).random()}"
    }
}