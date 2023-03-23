package com.candra.moneyapp.usecase

import com.candra.moneyapp.data.BudgetChanneled
import com.candra.moneyapp.data.Village
import kotlinx.coroutines.flow.Flow

interface VillageUseCase{
    /* Implementation Village */
    fun showAllRegency():Flow<List<Village>>
    suspend fun insertVillage(village: Village)
    suspend fun updateVillage(village: Village)
    suspend fun deleteVillage(village: Village)
    suspend fun deleteAllData()
    fun showAllVillage(substridict: String): Flow<List<Village>>

    /* Implementation BudgetChanneled */
    fun showAllDataFromIdVillage(idVillage: String): Flow<List<BudgetChanneled>>
    suspend fun deleteAll()
    suspend fun deleteBudgetChanneled(channeledData: BudgetChanneled)
    suspend fun updateBudgetChanneled(channeledData: BudgetChanneled)
    suspend fun insertBudgetChanneled(channeledData: BudgetChanneled)
    suspend fun deleteAllBudgetChanneledByIdVillage(idVillage: String)
}