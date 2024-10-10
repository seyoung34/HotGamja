package com.example.potatoservice.module

import com.example.potatoservice.model.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
	private const val BASE_URL = "https://e941a67f-eab3-400d-84ab-4dd8bc0bbebb.mock.pstmn.io/"
	@Provides
	@Singleton
	fun provideRetrofitService(retrofit: Retrofit): APIService {
		return retrofit.create(APIService::class.java)
	}

	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
}