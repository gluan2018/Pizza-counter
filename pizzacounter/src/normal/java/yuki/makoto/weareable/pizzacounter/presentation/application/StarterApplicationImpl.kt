package yuki.makoto.weareable.pizzacounter.presentation.application

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
class StarterApplicationImpl : StarterApplication {
    override fun start(context: Context) {

    }
}

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun starter(): StarterApplication = StarterApplicationImpl()
}