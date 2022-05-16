package com.example.moviepedia

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.model.Movie
import kotlinx.android.synthetic.main.moive_cell.view.*
import java.util.ArrayList

class MainAdapter(
        private val movies: List<Movie>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"

        private fun getGenre(gId: Int) : String{
            val genre = when(gId) {
                28 -> "Action"
                12 -> "Adventure"
                16 -> "Animation"
                35 -> "Comedy"
                80 -> "Crime"
                99 -> "Documentary"
                18 -> "Drama"
                10751 -> "Family"
                14 -> "Fantasy"
                36 -> "History"
                27 -> "Horror"
                10402 -> "Music"
                9648 -> "Mystery"
                10749 -> "Romance"
                878 -> "Science Fiction"
                10770 -> "TV Movie"
                53 -> "Thriller"
                10752 -> "War"
                37 -> "Western"
                else -> "gênero não informado"
            }
            return genre
        }

        fun bindMovie(movie: Movie){

            var genero = mutableListOf<String>()
            for (item in movie.genero)
            {
                genero.add(getGenre(item))
            }

            itemView.txt_movie_title.text = movie.title
            itemView.txt_release_date.text = movie.releaseDate
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.img_movie_poster)
            itemView.txt_genero.text = genero.joinToString(limit = 3) { it.toString() }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.moive_cell, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies.get(position)
        holder.bindMovie(movie)
        holder.itemView.setOnClickListener {
            val activity = holder.itemView.context as Activity
            var gList: List<Int> = movie.genero
            val genreList = ArrayList(gList)

            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra("id", movie.id)
            intent.putExtra("title", movie.title)
            intent.putExtra("poster", movie.poster)
            intent.putExtra("date", movie.releaseDate)
            intent.putIntegerArrayListExtra("genre", genreList)
            intent.putExtra("overview",movie.overview)
            intent.putExtra("language", movie.language)
            activity.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = movies.size

}

