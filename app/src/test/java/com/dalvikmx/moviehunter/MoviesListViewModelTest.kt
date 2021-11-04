package com.dalvikmx.moviehunter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dalvikmx.moviehunter.data.MovieDataRepository
import com.dalvikmx.moviehunter.data.Resource
import com.dalvikmx.moviehunter.data.errorhandler.NETWORK_ERROR
import com.dalvikmx.moviehunter.data.models.containers.MovieResponse
import com.dalvikmx.moviehunter.data.models.containers.MoviesListResponse
import com.dalvikmx.moviehunter.presentation.features.billboard.BillboardViewModel
import com.dalvikmx.moviehunter.util.InstantExecutorExtension
import com.dalvikmx.moviehunter.util.MainCoroutineRule
import com.dalvikmx.moviehunter.util.TestModelsGenerator
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class MoviesListViewModelTest {
    // Subject under test
    private lateinit var billboardViewModel: BillboardViewModel

    // Use a fake UseCase to be injected into the viewModel
    private val dataRepository: MovieDataRepository = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var movie: MovieResponse
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Before
    fun setUp() {
        // Create class under test
        // We initialise the repository with no tasks
        movie = testModelsGenerator.generateMoviesItemModel() ?: MovieResponse()
    }

    @Test
    fun `get Movies List`() {
        // Let's do an answer for the liveData
        val moviesModel = testModelsGenerator.generateMovieList()

        //1- Mock calls
        coEvery { dataRepository.requestPopularMoviesList() } returns flow {
            emit(Resource.Success(moviesModel))
        }

        //2-Call
        billboardViewModel = BillboardViewModel(dataRepository)
        billboardViewModel.getPopularList()
        //active observer for livedata
        billboardViewModel.moviesLiveData.observeForever { }

        //3-verify
        val isEmptyList = billboardViewModel.moviesLiveData.value?.data?.results.isNullOrEmpty()
        assertEquals(moviesModel, billboardViewModel.moviesLiveData.value?.data)
        assertEquals(false, isEmptyList)
    }

    @Test
    fun `get Movies Empty List`() {
        // Let's do an answer for the liveData
        val moviesModel = testModelsGenerator.generateMovieModelWithEmptyList()

        //1- Mock calls
        coEvery { dataRepository.requestPopularMoviesList() } returns flow {
            emit(Resource.Success(moviesModel))
        }

        //2-Call
        billboardViewModel = BillboardViewModel(dataRepository)
        billboardViewModel.getPopularList()
        //active observer for livedata
        billboardViewModel.moviesLiveData.observeForever { }

        //3-verify
        val isEmptyList = billboardViewModel.moviesLiveData.value?.data?.results.isNullOrEmpty()
        assertEquals(moviesModel, billboardViewModel.moviesLiveData.value?.data)
        assertEquals(true, isEmptyList)
    }

    @Test
    fun `get Movies Error`() {
        // Let's do an answer for the liveData
        val error: Resource<MoviesListResponse> = Resource.DataError(NETWORK_ERROR)

        //1- Mock calls
        coEvery { dataRepository.requestPopularMoviesList() } returns flow {
            emit(error)
        }

        //2-Call
        billboardViewModel = BillboardViewModel(dataRepository)
        billboardViewModel.getPopularList()
        //active observer for livedata
        billboardViewModel.moviesLiveData.observeForever { }

        //3-verify
        assertEquals(NETWORK_ERROR, billboardViewModel.moviesLiveData.value?.errorCode)
    }
}
