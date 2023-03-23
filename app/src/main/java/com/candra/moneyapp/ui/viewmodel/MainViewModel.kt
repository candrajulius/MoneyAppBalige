package com.candra.moneyapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.candra.moneyapp.data.LoginUser
import com.candra.moneyapp.helper.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel(){

   suspend fun loginAccount(context: Context,name: String, password: String) = viewModelScope.launch {
        Utils.updateDataUser(
            context, LoginUser(username = name, password = password,true)
        )
    }

    suspend fun logoutUser(context: Context) = viewModelScope.launch {
        Utils.updateDataUser(context,LoginUser("","",false))
    }

    fun getUserLogin(context: Context) = Utils.getUser(context).asLiveData()
}