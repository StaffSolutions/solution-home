package com.householdmanager.app.data.converters

import androidx.room.TypeConverter
import com.householdmanager.app.data.entity.*
import java.math.BigDecimal
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }

    @TypeConverter
    fun fromHouseType(value: HouseType?): String? {
        return value?.name
    }

    @TypeConverter
    fun toHouseType(value: String?): HouseType? {
        return value?.let { HouseType.valueOf(it) }
    }

    @TypeConverter
    fun fromBillType(value: BillType?): String? {
        return value?.name
    }

    @TypeConverter
    fun toBillType(value: String?): BillType? {
        return value?.let { BillType.valueOf(it) }
    }

    @TypeConverter
    fun fromRecurringInterval(value: RecurringInterval?): String? {
        return value?.name
    }

    @TypeConverter
    fun toRecurringInterval(value: String?): RecurringInterval? {
        return value?.let { RecurringInterval.valueOf(it) }
    }

    @TypeConverter
    fun fromTaskPriority(value: TaskPriority?): String? {
        return value?.name
    }

    @TypeConverter
    fun toTaskPriority(value: String?): TaskPriority? {
        return value?.let { TaskPriority.valueOf(it) }
    }

    @TypeConverter
    fun fromTaskStatus(value: TaskStatus?): String? {
        return value?.name
    }

    @TypeConverter
    fun toTaskStatus(value: String?): TaskStatus? {
        return value?.let { TaskStatus.valueOf(it) }
    }

    @TypeConverter
    fun fromTaskCategory(value: TaskCategory?): String? {
        return value?.name
    }

    @TypeConverter
    fun toTaskCategory(value: String?): TaskCategory? {
        return value?.let { TaskCategory.valueOf(it) }
    }

    @TypeConverter
    fun fromGroceryCategory(value: GroceryCategory?): String? {
        return value?.name
    }

    @TypeConverter
    fun toGroceryCategory(value: String?): GroceryCategory? {
        return value?.let { GroceryCategory.valueOf(it) }
    }

    @TypeConverter
    fun fromStorageLocation(value: StorageLocation?): String? {
        return value?.name
    }

    @TypeConverter
    fun toStorageLocation(value: String?): StorageLocation? {
        return value?.let { StorageLocation.valueOf(it) }
    }

    @TypeConverter
    fun fromMealType(value: MealType?): String? {
        return value?.name
    }

    @TypeConverter
    fun toMealType(value: String?): MealType? {
        return value?.let { MealType.valueOf(it) }
    }

    @TypeConverter
    fun fromHouseRole(value: HouseRole?): String? {
        return value?.name
    }

    @TypeConverter
    fun toHouseRole(value: String?): HouseRole? {
        return value?.let { HouseRole.valueOf(it) }
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.split(",")?.filter { it.isNotEmpty() }
    }
}