package acceptance

import com.home.messagebroker.DomainEvent
import com.home.messagebroker.DomainEventName
import com.home.messagebroker.DomainEventSubscriber
import com.home.messagebroker.DomainSubscriberRegistry
import com.home.rabbitmq.RabbitPublisher
import com.home.rabbitmq.useRabbit
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass

class RabbitPublishSubscribeFlowTest {

    private val subscriber1 = TestSubscriber1()
    private val subscriber2 = TestSubscriber2()

    @AfterEach
    fun tearDown() {
        RabbitAMPQTestUtils.deleteQueue(subscriber1.name(), subscriber2.name())
    }

    @Test
    fun `it raises event and subscribers are executed`() {
        DomainSubscriberRegistry.register(subscriber1, subscriber2)
        useRabbit()

        RabbitPublisher().publish(TestDomainEvent("id"))

        await().until {
            subscriber1.executions == 1 && subscriber2.executions == 1
        }
    }
}


class TestSubscriber1 : DomainEventSubscriber<TestDomainEvent>() {
    var executions = 0
    override fun on(event: TestDomainEvent) {
        executions++
    }

    override fun subscribedEvent(): KClass<TestDomainEvent> = TestDomainEvent::class
    override fun name() = "one.increase.on.test.event"
    override fun toEvent(domainEvent: DomainEvent) = domainEvent as TestDomainEvent
}

class TestSubscriber2 : DomainEventSubscriber<TestDomainEvent>() {
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