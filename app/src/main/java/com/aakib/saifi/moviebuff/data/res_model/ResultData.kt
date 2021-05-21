package com.aakib.saifi.moviebuff.data.res_model

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */


sealed class ResultData<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultData<T>()
    data class Error(val exception: Exception) : ResultData<Nothing>()
}