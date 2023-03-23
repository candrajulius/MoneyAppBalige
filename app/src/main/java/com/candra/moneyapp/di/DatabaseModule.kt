package com.candra.moneyapp.di

import android.content.Context
import androidx.room.Room
import com.candra.moneyapp.database.DatabaseVillage
import com.candra.moneyapp.database.dao.ChanneledDao
import com.candra.moneyapp.database.dao.VillageDao
import com.candra.moneyapp.helper.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context):DatabaseVillage =
        Room.databaseBuilder(
            context,
            DatabaseVillage::class.java,Constant.NAME_DATABASE
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

    @Provides
    fun providesVillageDao(database: DatabaseVillage): VillageDao = database.villageDao()

    @Provides
    fun providesBudgetChanneledDao(database: DatabaseVillage): ChanneledDao = database.budgetChanneledDao()
}