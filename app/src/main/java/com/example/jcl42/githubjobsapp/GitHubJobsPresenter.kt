package com.example.jcl42.githubjobsapp

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class GitHubJobsPresenter (val repo: GitHubRepo = GitHubRepo.instance, val view: GitHubJobsView) {

    private var disposable: Disposable? = null

    fun onTextEntered(searchTerm : String) {

        disposable?.dispose()

        if (searchTerm.isEmpty()) {
            view.setText("Enter a search query!")
            return
        }
        view.setText("Loading...")
        disposable = repo
                .getByDescription(searchTerm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { success, error ->
                    error?.let {
                        when (error) {
                            is GenericNetworkError -> {
                                // TODO -> Tell the user there is something wrong with the network connection
                            }
                            is ApiException -> {
                                // TODO -> Tell the user we got a response but the API returned an error (nothing wrong with the network connection)
                            }
                            else -> {
                                // TODO -> Something else happened
                            }
                        }
                    }
                    success?.let {
                        Log.d("JobSearchActivity", "${success.size} jobs found")
                    }
                }
    }
    }
