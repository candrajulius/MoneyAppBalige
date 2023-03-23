package com.candra.moneyapp.repository

import com.candra.moneyapp.data.BudgetChanneled
import com.candra.moneyapp.data.Village
import kotlinx.coroutines.flow.Flow

interface IRepository{
    /* Implementation Village */
    fun showAllRegency(): Flow<List<Village>>
    suspend fun insertVillage(village: Village)
    suspend fun updateVillage(village: Village)
    suspend fun deleteVillage(village: Village)
    suspend fun deleteAllData()
    fun showAllVillage(substridict: String): Flow<List<Village>>

    /* Impelementation */
    suspend fun insertBudgetChanneled(channeledData: BudgetChanneled)
    suspend fun deleteAll()
    suspend fun deleteBudgetChanneled(channeled: BudgetChanneled)
    suspend fun updateBudgetChanneled(channeledData: BudgetChanneled)
    fun showAllDataFromIdVillage(idVillage: String): Flow<List<BudgetChanneled>>
    suspend fun deleteAllByIdVillage(idVillage: String)
}