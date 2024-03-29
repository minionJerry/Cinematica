package com.kanykeinu.cinematica.ui.list

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.kanykeinu.cinematica.data.remote.MovieDbApi
import com.kanykeinu.cinematica.data.remote.responses.MovieInfoResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

const val MOVIES_COUNT_LIMIT = 20

class MovieListViewModel(private val api: MovieDbApi) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val error = MutableLiveData<String>()
    val movies = MutableLiveData<List<MovieInfoResponse>>()
    val isLoading = ObservableField<Boolean>(false)

    fun loadMovieChanges(startDate: String?=null, endDate: String?=null, page: Int=1) {
        if (movies.value != null && startDate == null) movies.value = movies.value
        else {
            isLoading.set(true)
            compositeDisposable.add(
                api.getMoviesChanges(startDate, endDate, page)
                    .map { results -> results.list.take(MOVIES_COUNT_LIMIT) }
                    .flatMapIterable { movieList -> movieList }
                    .filter { movieChanges -> !movieChanges.adult }
                    .flatMap { movieChanges ->
                        loadMovieInfo(movieChanges.id).onErrorResumeNext(Observable.empty())
                    }.toList().toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        movies.value = it
                    }, {
                        error.value = it.message
                        isLoading.set(false)
                    }, {
                        isLoading.set(false)
                    })
            )
        }
    }

    private fun loadMovieInfo(movieId: Int): Observable<MovieInfoResponse> {
        return api.getMovieInfo(movieId)
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }


}
