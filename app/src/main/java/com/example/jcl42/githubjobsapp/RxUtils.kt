package com.example.jcl42.githubjobsapp

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

object RxUtils {

    fun <T> networkTask(observable: Single<Result<T>>): Single<T> =
            observable
                    .subscribeOn(Schedulers.io())
                    .map { emitResponseBody(it) }

    fun <T> networkTaskWithResponse(observable: Single<Result<T>>): Single<Response<T>> =
            observable
                    .subscribeOn(Schedulers.io())
                    .map { emitResponse(it) }

    private fun <T> emitResponse(result: Result<T>) =
            if (result.isError) {
                throw GenericNetworkError(result.error())
            } else {
                if (result.response()?.isSuccessful == false) {
                    val bytes = result.response().errorBody()?.bytes()
                    bytes?.let {
                        throw ApiException(result.response().code(), String(it))
                    } ?: run {
                        throw ApiException(result.response().code())
                    }
                } else {
                    result.response()
                }
            }

    private fun <T> emitResponseBody(result: Result<T>) = emitResponse(result).body()
}


data class GenericNetworkError(val throwable: Throwable) : Throwable(throwable)
data class ApiException(val errorCode: Int, val errorBody: String = ""): Exception()

