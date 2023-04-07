package yuki.makoto.connection

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.NodeClient
import kotlinx.coroutines.*
import yuki.makoto.connection.interfaces.WearableClient
import yuki.makoto.connection.interfaces.WearableListener
import yuki.makoto.connection.util.Keys

internal class AppWearable constructor(
    private val messageClient: MessageClient,
    private val nodeClient: NodeClient
) : WearableClient, MessageClient.OnMessageReceivedListener {
    private val coroutineScope = CoroutineScope(SupervisorJob())
    private val listeners: MutableList<WearableListener> = mutableListOf()

    init {
        messageClient.addListener(this)
        coroutineScope.launch(Dispatchers.IO) {
            runCatching {
                val node = Tasks.await(nodeClient.localNode)
                Log.i("NodeWear", "Node: ${node.id}, Name: ${node.displayName}")
            }.onFailure { it.printStackTrace() }
        }
    }

    override fun onMessageReceived(p0: MessageEvent) {
        if (p0.path != Keys.PATH)
            return

        coroutineScope.launch {
            val currentNode = currentNode()
            if (p0.sourceNodeId == currentNode)
                return@launch
            notifyDataChange(p0.data)
        }
    }

    override suspend fun send(byteArray: ByteArray): Boolean {
        val result = withContext(Dispatchers.IO) {
            val nodeId = getNodeId() ?: return@withContext null
            runCatching {
                return@runCatching Tasks.await<Int?>(messageClient.sendMessage(nodeId, Keys.PATH, byteArray))
            }
        }

        return result != null && result.isSuccess
    }

    override fun addUpdateListener(listener: WearableListener) {
        listeners.add(listener)
    }

    override fun removeUpdateListener(listener: WearableListener) {
        listeners.remove(listener)
    }

    private fun getNodeId(): String? {
        return runCatching {
            val nodes = Tasks.await(nodeClient.connectedNodes)
            val node = nodes.firstOrNull { it.isNearby } ?: nodes.firstOrNull()

            return@runCatching node?.id
        }.onFailure { it.printStackTrace() }.getOrNull()
    }

    private fun currentNode(): String? = runCatching {
        Tasks.await(nodeClient.localNode).id
    }.getOrNull()

    private suspend fun notifyDataChange(byteArray: ByteArray) {
        withContext(Dispatchers.Main) {
            listeners.iterator().forEachRemaining { wearableListener ->
                wearableListener.onMessage(byteArray)
            }
        }
    }
}