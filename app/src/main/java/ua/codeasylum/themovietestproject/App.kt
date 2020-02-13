package ua.codeasylum.themovietestproject

import android.app.Application
import io.realm.Realm
import ua.codeasylum.themovietestproject.di.component.ApplicationComponent
import ua.codeasylum.themovietestproject.di.component.DaggerApplicationComponent

class App : Application() {


    lateinit var appComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initRealm()
        injectApp()
    }

    private fun initRealm() {
        Realm.init(applicationContext)
    }


    private fun injectApp() {
        appComponent = DaggerApplicationComponent
            .builder()
            .withApp(this)
            .build()
    }
}