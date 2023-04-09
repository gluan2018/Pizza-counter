package yuki.makoto.pizzacounter.application

import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import yuki.makoto.pizzacounter.BuildConfig
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
class StarterApplicationImpl : StarterApplication {
    override fun start(context: Context) {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .tag("App Pizza Counter")
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean = BuildConfig.DEBUG
        })
    }
}

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun starter(): StarterApplication = StarterApplicationImpl()
}