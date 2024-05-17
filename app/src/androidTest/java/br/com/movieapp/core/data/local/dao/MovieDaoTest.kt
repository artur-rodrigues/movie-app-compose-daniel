package br.com.movieapp.core.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import br.com.movieapp.core.data.local.MovieDataBase
import br.com.movieapp.core.data.local.entity.MovieEntity
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class MovieDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var dataBase: MovieDataBase
    private lateinit var movieDao: MovieDao

    @Before
    fun setup() {
        hiltRule.inject()
        movieDao = dataBase.movieDao()
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun test_getMovies_should_return_list_of_movies() = runTest {
        // Given - Nothing

        // When
        val movies = movieDao.getMovies().first()

        // Then
        assertThat(movies.size).isEqualTo(0)
    }

    @Test
    fun test_getMovies_should_return_movies_ordered_by_id() = runTest {
        // Given
        val list = listOfMovies().onEach {
            movieDao.insertMovie(it)
        }

        // When
        val movies = movieDao.getMovies().first()

        // Then
        assertThat(movies.size).isEqualTo(10)
        assertThat(movies[0].movieId).isEqualTo(1)
        assertThat(movies[1].movieId).isEqualTo(2)
        assertThat(movies[2].movieId).isEqualTo(3)
        assertThat(movies[3].movieId).isEqualTo(4)
        assertThat(movies[4].movieId).isEqualTo(5)
        assertThat(movies[5].movieId).isEqualTo(6)
        assertThat(movies[6].movieId).isEqualTo(7)
        assertThat(movies[7].movieId).isEqualTo(8)
        assertThat(movies[8].movieId).isEqualTo(9)
        assertThat(movies[9].movieId).isEqualTo(10)
    }

    @Test
    fun test_getMovies_should_return_correct_movie_by_id() = runTest {
        // Given
        val movieEntity = listOfMovies()[0]
        movieDao.insertMovie(movieEntity)

        val movies = movieDao.getMovies().first()
        val movieClicked = movies[0]

        // When
        val movieId = movieDao.isFavorite(movieClicked.movieId)

        // Then
        assertThat(movieId?.title).isEqualTo(movieClicked.title)
    }

    @Test
    fun test_insertMovies_should_insert_movies_successfully() = runTest {
        // Given
        val list = listOfMovies()

        // When
        list.forEach {
            movieDao.insertMovie(it)
        }

        // Then
        val movies = movieDao.getMovies().first()
        assertThat(list.size).isEqualTo(movies.size)
        assertThat(movies.containsAll(list)).isTrue()
    }

    @Test
    fun test_insertMovie_should_insert_movie_successfully() = runTest {
        // Given
        val movieEntity = listOfMovies()[0]

        // When
        movieDao.insertMovie(movieEntity)

        // Then
        val movie = movieDao.getMovies().first()[0]
        assertThat(movie.movieId).isEqualTo(movieEntity.movieId)
    }

    @Test
    fun test_isFavorite_should_return_favorite_movie_is_marked_as_favorite() = runTest {
        // Given
        val movieId = 5321
        val favoriteMovie = MovieEntity(
            movieId = movieId,
            title = "Homem de Ferro",
            imgUrl = "URL"
        )

        movieDao.insertMovie(favoriteMovie)

        // When
        val movie = movieDao.isFavorite(movieId)

        // Then
        assertThat(movie).isEqualTo(favoriteMovie)
    }

    @Test
    fun test_isFavorite_should_return_null_when_movie_is_not_marked_as_favorite() = runTest {
        // Given
        val movieId = 5321

        // When
        val movie = movieDao.isFavorite(movieId)

        // Then
        assertThat(movie).isNull()
    }

    @Test
    fun test_updateMovie_should_update_a_movie_successfully() = runTest {
        // Given
        val movieEntity = MovieEntity(
            movieId = 1,
            title = "Homem de Ferro",
            imgUrl = "URL"
        )
        movieDao.insertMovie(movieEntity )
        val allMovies = movieDao.getMovies().first()
        val updateMovie = allMovies[0].copy(title = "Não tem pra ninguém")
        
        // When
        movieDao.insertMovie(updateMovie)
        
        // Then
        val movies = movieDao.getMovies().first()

        assertThat(movies[0].title).contains(updateMovie.title)
    }

    @Test
    fun test_deleteMovie_should_delete_a_movie_successfully() = runTest {
        // Given
        val movieEntity = MovieEntity(
            movieId = 1,
            title = "Homem de Ferro",
            imgUrl = "URL"
        )

        movieDao.insertMovie(movieEntity )

        // When
        movieDao.deleteMovie(movieEntity)

        // Then
        val movie = movieDao.isFavorite(1)
        val movies = movieDao.getMovies().first()

        assertThat(movie).isNull()
        assertThat(movies.size).isEqualTo(0)
    }
        

    private fun listOfMovies(): List<MovieEntity> {
        val list = arrayListOf<MovieEntity>()
        for (i in 1..10) {
            list.add(MovieEntity(
                movieId = i,
                title = "Homem de Ferro $i",
                imgUrl = "URL"
            ))
        }
        return list
    }
}