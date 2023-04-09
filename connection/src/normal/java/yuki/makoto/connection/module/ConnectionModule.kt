package yuki.makoto.connection.module

import android.content.Context
import com.google.android.gms.wearable.Wearable
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean = BuildConfig.DEBUG
        })
    }
    @Provides
    fun create(@ApplicationContext context: Context): WearableClient = AppWearable(
        messageClient = Wearable.getMessageClient(context.applicationContext),
        nodeClient = Wearable.getNodeClient(context.applicationContext),
        logger = Logger.t("NodeWear")
    )
}