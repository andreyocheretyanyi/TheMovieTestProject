package ua.codeasylum.themovietestproject.di.component

import dagger.BindsInstance
import dagger.Component
import ua.codeasylum.themovietestproject.App
import ua.codeasylum.themovietestproject.di.module.AppModule
import ua.codeasylum.themovietestproject.di.scope.ApplicationScope

@ApplicationScope
@Component(
    modules = [
        AppModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun withApp(app : App) : Builder

        fun build () : ApplicationComponent
    }

    fun inject(app: App)
}