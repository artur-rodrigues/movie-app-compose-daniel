package br.com.movieapp.search_movie_feature.di

import br.com.movieapp.search_movie_feature.data.repository.MovieSearchRepositoryImpl
import br.com.movieapp.search_movie_feature.data.source.MovieSearchRemoteDataSourceImpl
import br.com.movieapp.search_movie_feature.domain.repository.MovieSearchRepository
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieSearchFeatureModule {

    @Binds
    abstract fun bindMovieSearchRemoteDataSource(dataSourceImpl: MovieSearchRemoteDataSourceImpl): MovieSearchRemoteDataSource

    @Binds
    abstract fun bindMovieSearchRepository(repositoryImpl: MovieSearchRepositoryImpl): MovieSearchRepository
}