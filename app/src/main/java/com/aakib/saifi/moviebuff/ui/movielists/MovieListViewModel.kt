package com.aakib.saifi.moviebuff.ui.movielists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aakib.saifi.moviebuff.data.res_model.MovieRes
import com.aakib.saifi.moviebuff.data.res_model.ResultData
import com.aakib.saifi.moviebuff.repository.AppRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

class MovieListViewModel @Inject constructor(
    private val repositoryImpl: AppRepositoryImpl
) : ViewModel() {


    private var _resultMovies = MutableLiveData<MovieRes>()
    var resultMovies : LiveData<MovieRes> = _resultMovies

    private var _errorMovies = MutableLiveData<String>()
    var errortMovies : LiveData<String> = _errorMovies

    private var _loadingMovies= MutableLiveData<Boolean>()
    var loadingMovies: LiveData<Boolean> = _loadingMovies

    fun getAllMovies(category: String,apiKey:String)
    {
        _loadingMovies.postValue(true)
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.getAllMovies(category, apiKey)) {
                    is ResultData.Success -> {
                        _loadingMovies.postValue(false)
                        _resultMovies.postValue(response.data)
                    }
                    is ResultData.Error -> {
                        _loadingMovies.postValue(false)
                        _errorMovies.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {
                _loadingMovies.postValue(false)
                _errorMovies.postValue(e.message)
            }
        }
    }

}