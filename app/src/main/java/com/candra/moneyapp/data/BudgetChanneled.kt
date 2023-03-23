package com.candra.moneyapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.candra.moneyapp.helper.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constant.TABLE_CHANNELED_BUDGET)
data class BudgetChanneled(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = Constant.ID_VILLAGE)
    val id_village: String,

    @ColumnInfo(name = Constant.CHANNELED)
    val channeled_budget: String,

    @ColumnInfo(name = Constant.RANGE_BUDGET)
    val range_budget: String,

    @ColumnInfo(name = Constant.MONTH)
    val month: String
):Parcelable