package xyz.leomurca.lmdb.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MovieId

@Module
@InstallIn(ViewModelComponent::class)
object DetailsModule {
    @Provides
    @MovieId
    @ViewModelScoped
    fun provideMovieId(savedStateHandle: SavedStateHandle): Long =
        savedStateHandle.get<Long>("movieId")
            ?: throw IllegalArgumentException("You have to provide movieId as parameter with type Long when navigating to details")
}
