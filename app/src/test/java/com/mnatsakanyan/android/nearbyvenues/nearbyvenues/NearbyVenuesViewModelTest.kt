package com.mnatsakanyan.android.nearbyvenues.nearbyvenues

import com.mnatsakanyan.android.nearbyvenues.MainDispatcherRule
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result.Error
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result.GPSError
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result.PermissionError
import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Result.Success
import com.mnatsakanyan.android.nearbyvenues.domain.venue.fake.FakeGetVenueRecommendationsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class NearbyVenuesViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    private val getVenueRecommendationsUseCase = FakeGetVenueRecommendationsUseCase()

    private lateinit var viewModel: NearbyVenuesViewModel

    @Before
    fun setUp() {
        viewModel = NearbyVenuesViewModel(getVenueRecommendationsUseCase)
    }

    @Test
    fun initEmitsLoadingState() = runTest {
        assertTrue { viewModel.uiState.value is NearbyVenuesUiState.Loading }
    }

    @Test
    fun onRefreshEmitsLoading() = runTest {
        viewModel.onRefresh()

        assertTrue { viewModel.uiState.value is NearbyVenuesUiState.Loading }
    }

    @Test
    fun onRefreshWithSuccessReturnsSuccess() = runTest {
        val collectorJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }
        getVenueRecommendationsUseCase.setResult(Success(listOf()))

        viewModel.onRefresh()

        assertTrue { viewModel.uiState.value is NearbyVenuesUiState.Venues }
        collectorJob.cancel()
    }

    @Test
    fun onRefreshWithErrorReturnsError() = runTest {
        val collectorJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }
        getVenueRecommendationsUseCase.setResult(Error(null))

        viewModel.onRefresh()

        assertTrue { viewModel.uiState.value is NearbyVenuesUiState.Error }
        collectorJob.cancel()
    }

    @Test
    fun onRefreshWithPermissionErrorReturnsPermissionError() = runTest {
        val collectorJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }
        getVenueRecommendationsUseCase.setResult(PermissionError(null))

        viewModel.onRefresh()

        assertTrue { viewModel.uiState.value is NearbyVenuesUiState.PermissionError }
        collectorJob.cancel()
    }

    @Test
    fun onRefreshWithGPSErrorReturnsGPSError() = runTest {
        val collectorJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }
        getVenueRecommendationsUseCase.setResult(GPSError(null))

        viewModel.onRefresh()

        assertTrue { viewModel.uiState.value is NearbyVenuesUiState.GPSError }
        collectorJob.cancel()
    }
}
