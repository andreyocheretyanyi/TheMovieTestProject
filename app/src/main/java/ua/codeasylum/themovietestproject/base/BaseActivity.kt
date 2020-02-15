package ua.codeasylum.themovietestproject.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.di.component.ActivityComponent
import ua.codeasylum.themovietestproject.di.component.DaggerActivityComponent

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent
            .builder()
            .withAppComponent((application as App).appComponent)
            .withActivity(this)
            .build()
    }
}