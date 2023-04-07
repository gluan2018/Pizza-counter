package yuki.makoto.weareable.pizzacounter.presentation.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AppApplication : Application() {

    @Inject
    lateinit var starterApplication: StarterApplication

    override fun onCreate() {
        super.onCreate()
        starterApplication.start(this)
    }

}