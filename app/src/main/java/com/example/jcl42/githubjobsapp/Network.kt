package com.example.jcl42.githubjobsapp

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.squareup.okhttp.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

object Network {

    val jacksonFactory: JacksonConverterFactory = JacksonConverterFactory.create(ignoreUnknownObjectMapper())
    private val rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create()

    fun <S> createService(serviceClass: Class<S>,
                          baseUrl: String): S {
        var retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(jacksonFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build()

        return retrofit.create(serviceClass)

    }
}

fun ignoreUnknownObjectMapper() : ObjectMapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)