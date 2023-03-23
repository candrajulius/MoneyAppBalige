package com.candra.moneyapp.data.source

import com.candra.moneyapp.data.BudgetChanneled
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.database.dao.ChanneledDao
import com.candra.moneyapp.database.dao.VillageDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val villageDao: VillageDao,
    private val budgetChanneledDao: ChanneledDao
){
    /* Impelementatio Village */
    fun showAllRegency(): Flow<List<Village>> = villageDao.showAllRegency()
    suspend fun insertVillage(village: Village) = villageDao.insertVillage(village)
    suspend fun updateVillage(village: Village) = villageDao.updateVillage(village)
    suspend fun deleteVillage(village: Village) = villageDao.deleteVillage(village)
    suspend fun deleteAllData() = villageDao.deleteAll()
    fun showAllVillage(substridict: String): Flow<List<Village>> = villageDao.showAllVillage(substridict)


    /* Implementation BudgetChanneled */
    fun showAllDataFromIdVillage(idVillage: String): Flow<List<BudgetChanneled>> =
        budgetChanneledDao.showAllDataFromIdVillage(idVillage)
    suspend fun deleteAll() = budgetChanneledDao.deleteAll()
    suspend fun deleteBudgetChanneled(channeledData: BudgetChanneled) =
        budgetChanneledDao.deleteBudgetChanneled(channeledData)
    suspend fun updateBudgetChanneled(channeledData: BudgetChanneled) =
        budgetChanneledDao.updateBudgetChanneled(channeledData)
    suspend fun insertBudgetChanneled(channeledData: BudgetChanneled) =
        budgetChanneledDao.insertBudgetChanneled(channeledData)
    suspend fun deleteAllBudgetChanneledByIdVillage(idVillage: String) =
        budgetChanneledDao.deleteAllByIdVillage(idVillage)
}