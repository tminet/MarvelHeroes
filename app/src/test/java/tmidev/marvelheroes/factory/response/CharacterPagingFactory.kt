package tmidev.marvelheroes.factory.response

import tmidev.core.domain.model.Character
import tmidev.core.domain.model.CharacterPaging

class CharacterPagingFactory {
    fun create() = CharacterPaging(
        offset = 0,
        total = 2,
        characters = listOf(
            Character(
                id = 1,
                name = "Hero 1",
                imageUrl = "url_hero_1.jpg"
            ),
            Character(
                id = 2,
                name = "Hero 2",
                imageUrl = "url_hero_2.jpg"
            ),
        )
    )
}