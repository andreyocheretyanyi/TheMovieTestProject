package ua.codeasylum.themovietestproject.di.module

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.Module
import dagger.Provides
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.di.scope.ActivityScope

@Module
class ActivityModule {


    @Provides
    @ActivityScope
    fun provideNavigation(activity: Activity) : NavController = activity.findNavController(R.id.nav_host_fragment)

}