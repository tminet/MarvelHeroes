package tmidev.testing.model

import tmidev.core.domain.model.Character

class CharactersFactory {
    fun create(character: Character) = when (character) {
        Character.Character1 -> Character(
            id = 1,
            name = "Hero 1",
            imageUrl = "url_hero_1.jpg"
        )
        Character.Character2 -> Character(
            id = 2,
            name = "Hero 2",
            imageUrl = "url_hero_2.jpg"
        )
    }

    sealed class Character {
        object Character1 : Character()
        object Character2 : Character()
    }
}