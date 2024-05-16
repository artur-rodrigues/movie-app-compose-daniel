package br.com.movieapp.core.di

import android.content.Context
import androidx.room.Room
import br.com.movieapp.core.data.local.MovieDataBase
import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.core.util.Constants.MOVIE_DATA_BASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object RoomModule {

    @Provides
    fun provideAppDataBases(@ApplicationContext context: Context): MovieDataBase {
        return Room.databaseBuilder(context, MovieDataBase::class.java, MOVIE_DATA_BASE_NAME).build()
    }

    @Provides
    fun provideMovieDao(dataBase: MovieDataBase): MovieDao {
        return dataBase.movieDao()
    }
}