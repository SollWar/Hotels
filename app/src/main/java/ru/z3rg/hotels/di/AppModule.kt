package ru.z3rg.hotels.di

import com.example.data.repository.HotelApiRepositoryImpl
import com.example.data.retrofit.HotelsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.z3rg.domain.repository.HotelApiRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHotelApi(): HotelsApi {
        return provideRetrofit()
            .create(HotelsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHotelApiRepository(hotelsApi: HotelsApi): HotelApiRepository {
        return HotelApiRepositoryImpl(hotelsApi)
    }
}