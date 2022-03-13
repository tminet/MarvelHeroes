package tmidev.testing.model

import tmidev.core.domain.model.Event

class EventFactory {
    fun create(fakeEvent: FakeEvent) = when (fakeEvent) {
        FakeEvent.FakeEvent1 -> Event(
            id = 1,
            imageUrl = "url_event_1.jpg"
        )
        FakeEvent.FakeEvent2 -> Event(
            id = 2,
            imageUrl = "url_event_2.jpg"
        )
    }

    sealed class FakeEvent {
        object FakeEvent1 : FakeEvent()
        object FakeEvent2 : FakeEvent()
    }
}