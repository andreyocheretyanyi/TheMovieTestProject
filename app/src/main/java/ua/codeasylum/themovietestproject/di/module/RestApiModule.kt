package ua.codeasylum.themovietestproject.di.module

import dagger.Module

import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ua.codeasylum.themovietestproject.BuildConfig
import ua.codeasylum.themovietestproject.base.service.MovieService
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope

@Module
class RestApiModule {

    @ApplicationScope
    @Provides
    fun provideMainRetrofitClient(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()


    @ApplicationScope
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}