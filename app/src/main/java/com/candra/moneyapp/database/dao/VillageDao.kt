package com.candra.moneyapp.database.dao

import androidx.room.*
import com.candra.moneyapp.data.Village
import kotlinx.coroutines.flow.Flow

@Dao
interface VillageDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVillage(village: Village)

    @Update
    suspend fun updateVillage(village: Village)

    @Delete
    suspend fun deleteVillage(village: Village)


    @Query("select * from table_desa group by kecamatan order by id asc")
    fun showAllRegency(): Flow<List<Village>>

    @Query("select * from table_desa where kecamatan like :substridict order by id asc")
    fun showAllVillage(substridict: String): Flow<List<Village>>


    @Query("delete from table_desa")
    suspend fun deleteAll()




}