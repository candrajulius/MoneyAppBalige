package com.candra.moneyapp.database.dao

import androidx.room.*
import com.candra.moneyapp.data.BudgetChanneled
import kotlinx.coroutines.flow.Flow

@Dao
interface ChanneledDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudgetChanneled(channeledData: BudgetChanneled)

    @Update
    suspend fun updateBudgetChanneled(channeledData: BudgetChanneled)

    @Delete
    suspend fun deleteBudgetChanneled(channeledData: BudgetChanneled)

    @Query("select * from table_anggaran_disalurkan where id_desa like :idVillage")
    fun showAllDataFromIdVillage(idVillage: String): Flow<List<BudgetChanneled>>

    @Query("delete from table_anggaran_disalurkan")
    suspend fun deleteAll()

    @Query("delete from table_anggaran_disalurkan where id_desa like :idVillage")
    suspend fun deleteAllByIdVillage(idVillage: String)
}