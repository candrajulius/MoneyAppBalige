package com.candra.moneyapp.helper

import android.content.Context
import android.text.format.DateFormat
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.RecyclerView
import com.candra.moneyapp.data.LoginUser
import com.candra.moneyapp.data.TempData
import com.candra.moneyapp.helper.Constant.ID_KEY_VILLAGE
import com.candra.moneyapp.helper.Constant.IS_LOGIN
import com.candra.moneyapp.helper.Constant.NAME_KEY_VILLAGE
import com.candra.moneyapp.helper.Constant.PASSWORD
import com.candra.moneyapp.helper.Constant.TOTAL_KEY_BUDGET
import com.candra.moneyapp.helper.Constant.USERNAME
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constant.LOGIN_SESSION_USER)

    fun getUser(context: Context): Flow<LoginUser> =
        context.dataStore.data.map { preferences ->
            LoginUser(
                username = preferences[USERNAME],
                password = preferences[PASSWORD],
                isLogin = preferences[IS_LOGIN]
            )
        }

    fun getIdVillage(context: Context): Flow<TempData> =
        context.dataStore.data.map { prefrences ->
            TempData(
                idVillage = prefrences[ID_KEY_VILLAGE],
                nameVillage = prefrences[NAME_KEY_VILLAGE],
                totalBudget = prefrences[TOTAL_KEY_BUDGET]
            )
        }

    suspend fun updateDataUser(context: Context, user: LoginUser) = context.dataStore.edit { preferences ->
        user.username?.let { preferences[USERNAME] = it }
        user.password?.let { preferences[PASSWORD] = it }
        user.isLogin?.let { preferences[IS_LOGIN] = it }
    }

    suspend fun updateIdVillage(context: Context, village: TempData) = context.dataStore.edit { preferences ->
        village.idVillage?.let { preferences[ID_KEY_VILLAGE] }
        village.nameVillage?.let { preferences[NAME_KEY_VILLAGE] }
        village.totalBudget?.let { preferences[TOTAL_KEY_BUDGET] }
    }

    fun makeToast(message: String,context: Context){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    fun convertToRupiah(price: Double): String{
        return "Rp " + NumberFormat.getNumberInstance(Locale.getDefault()).format(price)
    }

    fun greetingMessage(username: String): String {
       val cal = Calendar.getInstance()
        return when(cal.get(Calendar.HOUR_OF_DAY)){
            in 0..11 -> "Selamat pagi $username"
            in 12..15 -> "Selamat siang $username"
            in 16..18 -> "Selamat sore $username"
            in 18..23 -> "Selamat malam $username"
            else -> "Nothing data"
        }
    }

    fun showTextView(isSHow: Boolean,textView: MaterialTextView,rvList: RecyclerView){
        textView.visibility = if (isSHow) View.VISIBLE else View.GONE
        rvList.visibility = if (isSHow) View.GONE else View.VISIBLE
    }

    fun convertStringToMonth(month: String): String{
        val simpleDateFormat = SimpleDateFormat("MM",Locale.getDefault())
        return android.text.format.DateFormat.format("MMMM",simpleDateFormat.parse(month)).toString()
    }

    fun convertStringToYear(year: String): String{
        val simpleDateFormat = SimpleDateFormat("yyyy",Locale.getDefault())
        return android.text.format.DateFormat.format("yyyy",simpleDateFormat.parse(year)).toString()
    }

    fun convertMonthToString(month: String): String = SimpleDateFormat("MMMM",Locale.getDefault()).let {
        DateFormat.format("MM",it.parse(month)).toString()
    }
}