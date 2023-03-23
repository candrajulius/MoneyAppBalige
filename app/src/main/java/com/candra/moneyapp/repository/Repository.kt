package com.candra.moneyapp.repository

import com.candra.moneyapp.data.BudgetChanneled
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.data.source.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource
): IRepository {
    override fun showAllRegency(): Flow<List<Village>> =
        localDataSource.showAllRegency()

    override suspend fun insertVillage(village: Village) =
        localDataSource.insertVillage(village)

    override suspend fun updateVillage(village: Village) =
        localDataSource.updateVillage(village)

    override suspend fun deleteVillage(village: Village) =
        localDataSource.deleteVillage(village)

    override suspend fun deleteAllData()  =
        localDataSource.deleteAllData()

    override fun showAllVillage(substridict: String): Flow<List<Village>> =
        localDataSource.showAllVillage(substridict)

    override suspend fun insertBudgetChanneled(channeledData: BudgetChanneled) =
        localDataSource.insertBudgetChanneled(channeledData)

    override suspend fun deleteAll() = localDataSource.deleteAll()

    override suspend fun deleteBudgetChanneled(channeled: BudgetChanneled)  =
        localDataSource.deleteBudgetChanneled(channeled)

    override suspend fun updateBudgetChanneled(channeledData: BudgetChanneled) =
        localDataSource.updateBudgetChanneled(channeledData)

    override fun showAllDataFromIdVillage(idVillage: String): Flow<List<BudgetChanneled>> =
        localDataSource.showAllDataFromIdVillage(idVillage)

    override suspend fun deleteAllByIdVillage(idVillage: String) =
        localDataSource.deleteAllBudgetChanneledByIdVillage(idVillage)
}