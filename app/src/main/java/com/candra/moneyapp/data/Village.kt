package com.candra.moneyapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.candra.moneyapp.helper.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constant.TABLE_VILLAGE)
data class Village(
      @PrimaryKey(autoGenerate = true)
      val id: Int,

      @ColumnInfo(name = Constant.REGENCY)
      val regency: String,

      @ColumnInfo(name = Constant.ID_VILLAGE)
      val id_village: String,

      @ColumnInfo(name = Constant.NAME_VILLAGE)
      val name_village: String,

      @ColumnInfo(name = Constant.BUDGET)
      val total_budget: String,

      @ColumnInfo(name = Constant.YEAR_VILLAGE)
      val year_village: String,

      @ColumnInfo(name = Constant.BLT_SUSKEUDES_VILLAGE_JUNY)
      val blt_suskeudes_village_juny: String,

      @ColumnInfo(name = Constant.BLT_SUSKEUDES_VILLAGE_DECEMBER)
      val blt_suskeudes_village_december: String

): Parcelable