package br.com.movieapp.movie_favorite_feature.di

import br.com.movieapp.movie_favorite_feature.data.repository.MovieFavoriteRepositoryImpl
import br.com.movieapp.movie_favorite_feature.data.source.MovieFavoriteLocalDataSourceImpl
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieFavoriteFeatureModule {

    @Binds
    abstract fun bindMovieFavoriteLocalDataSource(localDataSource: MovieFavoriteLocalDataSourceImpl): MovieFavoriteLocalDataSource

    @Binds
    abstract fun bindMovieFavoriteRepository(repository: MovieFavoriteRepositoryImpl): MovieFavoriteRepository
}