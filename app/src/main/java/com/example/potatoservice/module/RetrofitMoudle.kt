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
	//개인 포스트맨 url
	private const val BASE_URL = "https://2840add0-e0da-40cd-9510-11698521f909.mock.pstmn.io"
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