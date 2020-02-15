package ua.codeasylum.themovietestproject.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope
import javax.inject.Named

@Module(
    includes = [
        RestApiModule::class
    ]
)
class AppModule {

    @Named("appContext")
    @ApplicationScope
    @Provides
    fun provideContext(app: App): Context = app.applicationContext

}