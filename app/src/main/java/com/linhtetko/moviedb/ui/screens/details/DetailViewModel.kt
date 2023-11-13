package com.linhtetko.moviedb.ui.screens.details

import android.util.Log.d
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.linhtetko.moviedb.data.remote.vos.MovieVO
import com.linhtetko.moviedb.domain.respositories.base.MovieRepository
import com.linhtetko.moviedb.ui.screens.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    var movie by mutableStateOf<MovieVO?>(null)
        private set

    fun getMovie(id: Int) {
        if (movie == null) {
            cmp.add(
                movieRepository.getMovie(id)
                    .doOnSubscribe {
                        loading = true
                    }
                    .subscribe(
                        {
                            loading = false
                            movie = it
                        },
                        {
                            loading = false
                            error = it.localizedMessage
                        }
                    )
            )
        }
    }

    fun updateMovie() {
        if (movie != null) {
            val modifiedState = !movie!!.isFavourite
            cmp.add(
                movieRepository.updateMovie(movie = movie!!.copy(isFavourite = modifiedState))
                    .subscribe({
                        message = "${if (modifiedState) "Add To " else "Remove From "} Successfully"

                        viewModelScope.launch(Dispatchers.Default) {
                            delay(2000)
                            message = null
                        }
                    }, {
                        error = it.localizedMessage
                    })
            )
        }
    }

    override fun onCleared() {
        cmp.dispose()
        super.onCleared()
    }
}