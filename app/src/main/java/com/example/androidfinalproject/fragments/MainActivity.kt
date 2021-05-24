package com.example.androidfinalproject.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidfinalproject.api.ServiceBuilder
import com.example.androidfinalproject.api.TmdbEndpoints
import com.example.androidfinalproject.views.MoviesAdapter
import com.example.androidfinalproject.R
import com.example.androidfinalproject.models.PopularMovies
import com.example.androidfinalproject.util.Constants.Companion.api_key
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val phoneFragment = PhoneFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flTest, phoneFragment)
            commit()
        }
        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getMovies(api_key)

        call.enqueue(object : Callback<PopularMovies>{
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                if (response.isSuccessful){
                    progress_bar.visibility = View.GONE
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        adapter = MoviesAdapter(response.body()!!.results)
                    }
                }
            }
            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

