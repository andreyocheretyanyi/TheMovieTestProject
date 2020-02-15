package ua.codeasylum.themovietestproject.di.component

import android.app.Activity
import dagger.BindsInstance
import dagger.Component
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.di.module.ActivityModule
import ua.codeasylum.themovietestproject.di.module.RepositoryModule
import ua.codeasylum.themovietestproject.di.module.ViewModelModule
import ua.codeasylum.themovietestproject.di.scope.ActivityScope


@ActivityScope
@Component(
    modules = [ViewModelModule::class, RepositoryModule::class, ActivityModule::class],
    dependencies = [ApplicationComponent::class]
)
interface ActivityComponent {

    fun inject(fragment: BaseFragment)

    @Component.Builder
    interface Builder {


        @BindsInstance
        fun withActivity(activity: Activity): Builder

        fun withAppComponent(appComponent : ApplicationComponent) : Builder

        fun build(): ActivityComponent
    }
}