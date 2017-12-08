package com.example.jcl42.githubjobsapp

import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Query

class GitHubRepo(val service: PositionService = Network.createService(PositionService::class.java, "https://jobs.github.com/")) {
    companion object {
        val instance: GitHubRepo by lazy {
            GitHubRepo()
        }
    }

    fun getByDescription(description: String): Single<List<GitHubJob>> {
        return RxUtils.networkTask(service.getPositions(description = description))
    }
}


interface PositionService {
    @GET("positions.json")
    fun getPositions(@Query("description") description: String? = null,
                     @Query("location") location: String? = null): Single<Result<List<GitHubJob>>>
}
