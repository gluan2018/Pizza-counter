package yuki.makoto.weareable.pizzacounter.application

import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yuki.makoto.weareable.pizzacounter.presentation.application.StarterApplication
import javax.inject.Singleton

@Singleton
class StarterApplicationImpl : StarterApplication {
    override fun start(context: Context) {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .tag("Wearable App")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
}

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun starter(): StarterApplication = StarterApplicationImpl()
}