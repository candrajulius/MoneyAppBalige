package com.candra.moneyapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.candra.moneyapp.data.BudgetChanneled
import com.candra.moneyapp.data.TempData
import com.candra.moneyapp.helper.Utils
import com.candra.moneyapp.usecase.VillageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChanneledBudgetViewModel @Inject constructor(
    private val villageUseCase: VillageUseCase
): ViewModel()
{
    fun showAllDataFromIdVillage(idVillage: String) = villageUseCase.showAllDataFromIdVillage(idVillage).asLiveData()
    fun getIdVillage(context: Context) = Utils.getIdVillage(context).asLiveData()

    suspend fun deleteAll() = viewModelScope.launch {
        villageUseCase.deleteAll()
    }
    suspend fun deleteBudgetChanneled(channelData: BudgetChanneled) = viewModelScope.launch {
        villageUseCase.deleteBudgetChanneled(channelData)
    }
    suspend fun insertBudgetChanneled(channeled: BudgetChanneled) = viewModelScope.launch {
        villageUseCase.insertBudgetChanneled(channeled)
    }
    suspend fun updateBudgetChanneled(channeled: BudgetChanneled) = viewModelScope.launch {
        villageUseCase.updateBudgetChanneled(channeled)
    }
    suspend fun deleteAllBudgetChanneledByIdVillage(idVillage: String) = viewModelScope.launch {
        villageUseCase.deleteAllBudgetChanneledByIdVillage(idVillage)
    }

    suspend fun updateIdVillage(context: Context,village: TempData) = viewModelScope.launch {
        Utils.updateIdVillage(context,village)
    }

}