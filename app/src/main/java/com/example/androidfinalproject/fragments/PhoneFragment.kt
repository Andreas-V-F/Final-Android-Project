package com.example.androidfinalproject.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PhoneFragment : Fragment(R.layout.activity_main), MoviesAdapter.OnItemClickListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getMovies(Constants.api_key)

        call.enqueue(object : Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful) {
                    progress_bar.visibility = View.GONE
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(activity, 2)
                        adapter = MoviesAdapter(response.body()!!.results, this@PhoneFragment)
                    }
                }
            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onItemClick(position: Int, result: Result) {
        val overviewFragment = OverviewFragment(result)

        if (resources.getBoolean(R.bool.isTablet)) {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.flOverview, overviewFragment).addToBackStack(null).commit()

        } else {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.flTest, overviewFragment).addToBackStack(null).commit()

        }

    }
}