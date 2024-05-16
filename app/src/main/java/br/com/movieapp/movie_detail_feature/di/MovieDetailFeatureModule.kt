package br.com.movieapp.movie_detail_feature.di

import br.com.movieapp.movie_detail_feature.data.repository.MovieDetailRepositoryImpl
import br.com.movieapp.movie_detail_feature.data.source.MovieDetailRemoteDataSourceImpl
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailRepository
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieDetailFeatureModule {

    @Binds
    abstract fun bindMovieDetailRemoteDataSource(dataSourceImpl: MovieDetailRemoteDataSourceImpl): MovieDetailRemoteDataSource

    @Binds
    abstract fun bindMovieDetailRepository(repositoryImpl: MovieDetailRepositoryImpl): MovieDetailRepository
}