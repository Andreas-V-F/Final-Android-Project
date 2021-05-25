package com.example.androidfinalproject.views


import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinalproject.R
import com.example.androidfinalproject.models.Result
import com.squareup.picasso.Picasso


class MoviesAdapter(
    val movies: List<Result>,
    val listener: OnItemClickListener
    ):
        RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        return holder.bind(movies[position])
    }
    inner class MoviesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val photo:ImageView = itemView.findViewById(R.id.movie_photo)
        private val title:TextView = itemView.findViewById(R.id.movie_title)
        private val rating:TextView = itemView.findViewById(R.id.movie_rating)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position, movies[position])
            }
        }

        fun bind(movie: Result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${movie.poster_path}").into(photo);
            val boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD)
            title.typeface = boldTypeface
            title.text = movie.title
            rating.text = "Rating : "+movie.vote_average.toString()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, result: Result)
    }
}

