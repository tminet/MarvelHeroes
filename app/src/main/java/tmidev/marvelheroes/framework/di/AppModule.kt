package tmidev.marvelheroes.framework.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import tmidev.marvelheroes.framework.imageloader.ImageLoader
import tmidev.marvelheroes.framework.imageloader.ImageLoaderImplGlide

@Module
@InstallIn(FragmentComponent::class)
interface AppModule {
    @Binds
    fun bindImageLoader(
        imageLoader: ImageLoaderImplGlide
    ): ImageLoader
}