package com.kunila.ocean.di

import android.content.Context
import com.kunila.ocean.data.datasource.CsvDataSource
import com.kunila.ocean.data.datasource.ProductDataSource
import com.kunila.ocean.data.repository.CsvRepository
import com.kunila.ocean.data.repository.ProductRepository
import com.kunila.ocean.domain.usecase.GetPollutionDataUseCase
import com.kunila.ocean.domain.usecase.GetProductDataUseCase
import com.kunila.ocean.domain.usecase.GetTemperatureDataUseCase
import com.kunila.ocean.domain.usecase.GetWindDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCsvDataSource(@ApplicationContext context: Context): CsvDataSource {
        return CsvDataSource(context)
    }

    @Provides
    @Singleton
    fun provideCsvRepository(csvDataSource: CsvDataSource): CsvRepository {
        return CsvRepository(csvDataSource)
    }

    @Provides
    @Singleton
    fun provideProductDataSource(): ProductDataSource {
        return ProductDataSource()
    }

    @Provides
    @Singleton
    fun provideProductRepository(productDataSource: ProductDataSource): ProductRepository {
        return ProductRepository(productDataSource)
    }

    @Provides
    @Singleton
    fun provideGetPollutionDataUseCase(csvRepository: CsvRepository): GetPollutionDataUseCase {
        return GetPollutionDataUseCase(csvRepository)
    }

    @Provides
    @Singleton
    fun provideGetWindSpeedDataUseCase(csvRepository: CsvRepository): GetWindDataUseCase {
        return GetWindDataUseCase(csvRepository)
    }

    @Provides
    @Singleton
    fun provideGetTemperatureDataUseCase(csvRepository: CsvRepository): GetTemperatureDataUseCase {
        return GetTemperatureDataUseCase(csvRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductDataUseCase(productRepository: ProductRepository): GetProductDataUseCase {
        return GetProductDataUseCase(productRepository)
    }
}