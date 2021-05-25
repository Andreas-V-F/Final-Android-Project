package com.example.androidfinalproject.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidfinalproject.R
import com.example.androidfinalproject.api.ServiceBuilder
import com.example.androidfinalproject.api.TmdbEndpoints
import com.example.androidfinalproject.models.PopularMovies
import com.example.androidfinalproject.models.Result
import com.example.androidfinalproject.util.Constants
import com.example.androidfinalproject.views.MoviesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment(val search: String) : Fragment(R.layout.activity_main), MoviesAdapter.OnItemClickListener {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.searchMovies(Constants.api_key, search)

        call.enqueue(object : Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful) {
                    progress_bar.visibility = View.GONE
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(activity, 2)
                        adapter = MoviesAdapter(response.body()!!.results, this@SearchFragment)
                    }
                }
            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
        val editText = activity!!.findViewById<TextView>(R.id.tvMainTitle)
        editText.text = "Searched for: $search"
        if(resources.getBoolean(R.bool.isTablet)){
            val flLayout = activity!!.findViewById<FrameLayout>(R.id.flTest)
            val frameLayoutParams = flLayout.layoutParams
            frameLayoutParams.width = activity!!.windowManager.currentWindowMetrics.bounds.right - 20
            flLayout.requestLayout()
            flLayout.forceLayout()

        }

    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onItemClick(position: Int, result: Result) {
        val overviewFragment = OverviewFragment(result)

        if (resources.getBoolean(R.bool.isTablet)) {
            val flLayout = activity!!.findViewById<FrameLayout>(R.id.flTest)
            val frameLayoutParams = flLayout.layoutParams
            frameLayoutParams.width = activity!!.windowManager.currentWindowMetrics.bounds.right / 2
            flLayout.requestLayout()
            flLayout.forceLayout()
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.flOverview, overviewFragment).addToBackStack(null).commit()

        } else {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.flTest, overviewFragment).addToBackStack(null).commit()
        }

    }
}