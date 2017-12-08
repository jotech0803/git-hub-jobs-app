package com.example.jcl42.githubjobsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_jobs_search.*
import org.jetbrains.anko.onClick

class JobsSearchActivity : AppCompatActivity(), GitHubJobsView {
    private val presenter = GitHubJobsPresenter(view = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs_search)

        search_btn.onClick {
            val searchTerm = search_edit.text.toString()
            presenter.onTextEntered(searchTerm)
        }
    }

    override fun setText(text: String) {
        tweet_txt.text = text
    }
}







