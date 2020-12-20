package acceptance

import com.rabbitmq.client.ConnectionFactory

object RabbitAMPQTestUtils {
    private val connection = ConnectionFactory().newConnection("amqp://guest:guest@localhost:5672/")
    private val channel = connection.createChannel()

    fun deleteQueue(vararg name: String) {
        name.forEach { channel.queueDeleteNoWait(it, false, false) }
    }
}