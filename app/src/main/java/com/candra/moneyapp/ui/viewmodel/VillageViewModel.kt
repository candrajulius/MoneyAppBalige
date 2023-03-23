package com.candra.moneyapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.candra.moneyapp.data.Village
import com.candra.moneyapp.usecase.VillageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VillageViewModel @Inject constructor(
    private val villageUseCase: VillageUseCase
): ViewModel()
{
    val showAllRegency = villageUseCase.showAllRegency().asLiveData()
    fun showAllVillage(substridict: String) = villageUseCase.showAllVillage(substridict).asLiveData()
    suspend fun insertVillage(village: Village) = viewModelScope.launch {
        villageUseCase.insertVillage(village)
    }

    suspend fun updateVillage(village: Village) = viewModelScope.launch {
        villageUseCase.updateVillage(village)
    }

    suspend fun deleteVillage(village: Village) = viewModelScope.launch {
        villageUseCase.deleteVillage(village)
    }
    suspend fun deleteAllData() = viewModelScope.launch {
        villageUseCase.deleteAllData()
    }
}