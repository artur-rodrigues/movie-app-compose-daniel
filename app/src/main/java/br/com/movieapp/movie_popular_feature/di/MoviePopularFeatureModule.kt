package br.com.movieapp.movie_popular_feature.di

import br.com.movieapp.movie_popular_feature.data.repository.MoviePopularRepositoryImpl
import br.com.movieapp.movie_popular_feature.data.source.MoviePopularRemoteDataSourceImpl
import br.com.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import br.com.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MoviePopularFeatureModule {

    @Binds
    abstract fun bindMoviePopularRemoteDataSource(dataSourceImpl: MoviePopularRemoteDataSourceImpl): MoviePopularRemoteDataSource

    @Binds
    abstract fun bindMoviePopularRepository(repositoryImpl: MoviePopularRepositoryImpl): MoviePopularRepository
}