package ua.codeasylum.themovietestproject

import android.app.Application
import ua.codeasylum.themovietestproject.di.component.ApplicationComponent
import ua.codeasylum.themovietestproject.di.component.DaggerApplicationComponent

class App : Application() {


    lateinit var appComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        injectApp()
    }



    private fun injectApp() {
        appComponent = DaggerApplicationComponent
            .builder()
            .withApp(this)
            .build()
    }
}