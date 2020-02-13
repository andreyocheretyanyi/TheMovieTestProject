package ua.codeasylum.themovietestproject.di.component

import dagger.Component
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.di.module.RepositoryModule
import ua.codeasylum.themovietestproject.di.module.ViewModelModule
import ua.codeasylum.themovietestproject.di.scope.ActivityScope


@ActivityScope
@Component(
    modules = [ViewModelModule::class, RepositoryModule::class],
    dependencies = [ApplicationComponent::class]
)
interface ActivityComponent {

    fun inject(fragment: BaseFragment)
}