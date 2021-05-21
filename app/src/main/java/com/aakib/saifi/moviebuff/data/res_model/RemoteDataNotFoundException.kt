package com.aakib.saifi.moviebuff.data.res_model

/**
 * Created by Aakib
 * Date : 21/May/2021
 * Project : MovieBuff
 */

open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataSourceException("Data not found in remote data source")