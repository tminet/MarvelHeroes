package tmidev.testing.model

import tmidev.core.domain.model.Comic

class ComicFactory {
    fun create(fakeComic: FakeComic) = when (fakeComic) {
        FakeComic.FakeComic1 -> Comic(
            id = 1,
            imageUrl = "url_comic_1.jpg"
        )
        FakeComic.FakeComic2 -> Comic(
            id = 2,
            imageUrl = "url_comic_2.jpg"
        )
    }

    sealed class FakeComic {
        object FakeComic1 : FakeComic()
        object FakeComic2 : FakeComic()
    }
}