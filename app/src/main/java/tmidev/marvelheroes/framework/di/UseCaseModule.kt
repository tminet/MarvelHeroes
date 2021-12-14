package tmidev.marvelheroes.framework.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tmidev.core.domain.usecase.GetCharactersUseCase
import tmidev.core.domain.usecase.GetCharactersUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetCharactersUseCase(
        useCase: GetCharactersUseCaseImpl
    ): GetCharactersUseCase
}