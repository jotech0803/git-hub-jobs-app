package com.example.jcl42.githubjobsapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubJob(val title: String,
                     val location: String) : Parcelable
