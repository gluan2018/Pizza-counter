package yuki.makoto.connection.module

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import yuki.makoto.connection.AppWearable
import yuki.makoto.connection.interfaces.WearableClient

@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class, ViewModelComponent::class)
class ConnectionModule {
    init {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .tag("NodeWear")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
    @Provides
    fun create(): WearableClient = AppWearable()
}