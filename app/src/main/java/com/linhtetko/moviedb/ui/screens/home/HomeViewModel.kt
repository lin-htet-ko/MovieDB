package com.linhtetko.moviedb.ui.screens.home

import android.util.Log.d
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linhtetko.moviedb.data.remote.vos.MovieVO
import com.linhtetko.moviedb.domain.respositories.base.MovieRepository
import com.linhtetko.moviedb.ui.screens.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    var popularMoviesState: List<MovieVO> by mutableStateOf(listOf())
        private set

    var upComingMovieState: List<MovieVO> by mutableStateOf(listOf())
        private set


    init {
        getPopularMovies()
        getUpComingMovies()
    }

    fun getPopularMovies() {
        val popularCmp = movieRepository.getPopularMovies(cmp = cmp,
            onFailure = {
                error = it
            })
            .doOnSubscribe {
                loading = true
            }
            .subscribe({
                loading = false
                popularMoviesState = it
            }, {
                loading = false
                error = it.localizedMessage
            })
        cmp.add(popularCmp)
    }

    fun getUpComingMovies() {
        val upComingCmp = movieRepository.getUpComingMovies(cmp = cmp,
            onFailure = {
                error = it
            })
            .doOnSubscribe {
                loading = true
            }
            .subscribe(
                {
                    loading = false
                    upComingMovieState = it
                },
                {
                    loading = false
                    error = it.localizedMessage
                }
            )
        cmp.add(upComingCmp)
    }

    fun onTapFav(entity: MovieVO) {
        val modifiedState = !entity.isFavourite
        val updateCmp = movieRepository.updateMovie(entity.copy(isFavourite = modifiedState))
            .subscribe(
                {
                    message =
                        "${if (modifiedState) "Add" else "Remove"} Favourite Successfully"
                    viewModelScope.launch(Dispatchers.Default) {
                        delay(2000)
                        withContext(Dispatchers.Main) {
                            message = null
                        }
                    }
                },
                {
                    error = it.localizedMessage
                }
            )
        cmp.add(updateCmp)
    }

    override fun onCleared() {
        cmp.dispose()
        super.onCleared()
    }


}