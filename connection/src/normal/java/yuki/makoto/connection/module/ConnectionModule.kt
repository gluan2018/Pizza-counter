package yuki.makoto.connection.module

import android.content.Context
import com.google.android.gms.wearable.Wearable
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
    @Provides
    fun create(@ApplicationContext context: Context): WearableClient = AppWearable(
        messageClient = Wearable.getMessageClient(context.applicationContext),
        nodeClient = Wearable.getNodeClient(context.applicationContext)
    )
}