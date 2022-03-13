package tmidev.testing.model

import tmidev.core.domain.model.Character

class CharactersFactory {
    fun create(fakeCharacter: FakeCharacter) = when (fakeCharacter) {
        FakeCharacter.FakeCharacter1 -> Character(
            id = 1,
            name = "Hero 1",
            imageUrl = "url_hero_1.jpg"
        )
        FakeCharacter.FakeCharacter2 -> Character(
            id = 2,
            name = "Hero 2",
            imageUrl = "url_hero_2.jpg"
        )
    }

    sealed class FakeCharacter {
        object FakeCharacter1 : FakeCharacter()
        object FakeCharacter2 : FakeCharacter()
    }
}