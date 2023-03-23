package com.candra.moneyapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.candra.moneyapp.data.BudgetChanneled
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.database.dao.ChanneledDao
import com.candra.moneyapp.database.dao.VillageDao

@Database(entities = [Village::class,BudgetChanneled::class], version = 1, exportSchema = false)
abstract class DatabaseVillage: RoomDatabase(){
    abstract fun villageDao(): VillageDao
    abstract fun budgetChanneledDao(): ChanneledDao
}