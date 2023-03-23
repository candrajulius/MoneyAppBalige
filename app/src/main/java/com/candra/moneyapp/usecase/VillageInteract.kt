package com.candra.moneyapp.usecase

import com.candra.moneyapp.data.BudgetChanneled
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VillageInteract @Inject constructor(
    private val repository: Repository
): VillageUseCase {
    override fun showAllRegency(): Flow<List<Village>> {
        return repository.showAllRegency()
    }

    override suspend fun insertVillage(village: Village) {
        return repository.insertVillage(village)
    }

    override suspend fun updateVillage(village: Village) {
       return repository.updateVillage(village)
    }

    override suspend fun deleteVillage(village: Village) {
        return repository.deleteVillage(village)
    }

    override suspend fun deleteAllData() {
        return repository.deleteAllData()
    }

    override fun showAllVillage(substridict: String): Flow<List<Village>> {
        return repository.showAllVillage(substridict)
    }

    override fun showAllDataFromIdVillage(idVillage: String): Flow<List<BudgetChanneled>> {
        return repository.showAllDataFromIdVillage(idVillage)
    }

    override suspend fun deleteAll() {
        return repository.deleteAll()
    }

    override suspend fun deleteBudgetChanneled(channeledData: BudgetChanneled) {
        return repository.deleteBudgetChanneled(channeledData)
    }

    override suspend fun updateBudgetChanneled(channeledData: BudgetChanneled) {
        return repository.updateBudgetChanneled(channeledData)
    }

    override suspend fun insertBudgetChanneled(channeledData: BudgetChanneled) {
        return repository.insertBudgetChanneled(channeledData)
    }

    override suspend fun deleteAllBudgetChanneledByIdVillage(idVillage: String) {
        return repository.deleteAllByIdVillage(idVillage)
    }
}