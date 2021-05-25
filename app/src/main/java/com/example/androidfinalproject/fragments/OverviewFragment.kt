package com.example.androidfinalproject.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.androidfinalproject.R
import com.example.androidfinalproject.models.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_overview.*


class OverviewFragment(val result: Result) : Fragment(R.layout.fragment_overview){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = result.title
        tvOverview.text = result.overview
        tvRating.text = result.vote_average.toString()
        tvRelease.text = result.release_date
        Picasso.get().load("https://image.tmdb.org/t/p/w780${result.poster_path}").into(ivPoster);

    }
}