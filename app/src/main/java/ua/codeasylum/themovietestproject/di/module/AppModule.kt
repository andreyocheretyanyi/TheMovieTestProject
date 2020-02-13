package ua.codeasylum.themovietestproject.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope

@Module
class AppModule() {

    @ApplicationScope
    @Provides
    fun provideContext(app : App): Context = app.applicationContext

}