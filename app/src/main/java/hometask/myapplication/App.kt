package hometask.myapplication

import android.app.Application
import hometask.myapplication.di.AppComponent
import hometask.myapplication.di.AppModule
import hometask.myapplication.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
    }
}
