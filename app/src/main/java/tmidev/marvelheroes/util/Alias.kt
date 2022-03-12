package tmidev.marvelheroes.util

import android.view.View
import tmidev.core.domain.model.Character

typealias OnCharacterItemClick = (character: Character, view: View) -> Unit