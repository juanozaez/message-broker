package acceptance

import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainEventName
import com.home.messagebroker.DomainSubscriber
import com.home.messagebroker.DomainSubscriberRegistry
import com.home.rabbitmq.RabbitConsumerRegisterer
import com.home.rabbitmq.RabbitProducer
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass

class SubscriberTest {

    private val subscriber1 = TestSubscriber1()
    private val subscriber2 = TestSubscriber2()

    @Test
    fun `it raises event and subscribers are executed`() {
        listOf(subscriber1, subscriber2).forEach(DomainSubscriberRegistry::register)
        RabbitConsumerRegisterer().registerSubscribers()

        RabbitProducer().publish(TestDomainEvent("id"))

        await().until {
            subscriber1.executions == 1 && subscriber2.executions == 1
        }
    }
}


class TestSubscriber1 : DomainSubscriber<TestDomainEvent>() {
    var executions = 0
    override fun on(event: TestDomainEvent) {
        executions++
    }

    override fun subscribedEvent(): KClass<TestDomainEvent> = TestDomainEvent::class
    override fun name() = "one.increase.on.test.event"
    override fun toEvent(domainEvent: DomainEvent) = domainEvent as TestDomainEvent
}

class TestSubscriber2 : DomainSubscriber<TestDomainEvent>() {
    var executions = 0
    override fun on(event: TestDomainEvent) {
        executions++
    }

    override fun subscribedEvent(): KClass<TestDomainEvent> = TestDomainEvent::class
    override fun name() = "two.increase.on.test.event"
    override fun toEvent(domainEvent: DomainEvent) = domainEvent as TestDomainEvent
}


@DomainEventName("test.event")
class TestDomainEvent(override val aggregateId: String) : DomainEvent(aggregateId) {
    override fun name() = "test.event"
}