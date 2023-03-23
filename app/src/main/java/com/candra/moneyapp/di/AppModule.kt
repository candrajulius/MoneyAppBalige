package com.candra.moneyapp.di

import com.candra.moneyapp.usecase.VillageInteract
import com.candra.moneyapp.usecase.VillageUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideVillageUseCase(villageInteract: VillageInteract): VillageUseCase
}