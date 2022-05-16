package com.example.moviepedia

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepedia.model.Movie
import com.example.moviepedia.model.MovieDetails
import com.example.moviepedia.model.MovieResponse
import com.example.moviepedia.util.ApiInterface
import com.example.moviepedia.util.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.moive_cell.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {

    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail_activity)

        val bundle: Bundle? = intent.extras

        val imgPoster = findViewById(R.id.img_poster_detail) as ImageView
        val date = findViewById(R.id.txt_release) as TextView
        val genre = findViewById(R.id.txt_genre_detail) as TextView
        val overview = findViewById(R.id.txt_overview) as TextView
        val language = findViewById(R.id.txt_language) as TextView
        val title = findViewById(R.id.txt_title) as TextView

        fun getGenre(gId : Int) : String{
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

        val genero = mutableListOf<String>()

        for (item in bundle?.getIntegerArrayList("genre")!!)
        {
            genero.add(getGenre(item))
        }

        Glide.with(imgPoster).load(IMAGE_BASE + bundle?.get("poster")).into(imgPoster)
        date.text = bundle?.get("date").toString()
        if(!genero.isNullOrEmpty()){
            genre.text = genero.joinToString() { it.toString() }
        }
        if(!bundle?.get("overview").toString().isEmpty() || bundle?.get("overview").toString() != ""){
            overview.text = bundle?.get("overview").toString()
        }
        language.text = bundle?.get("language").toString()
        title.text = bundle?.get("title").toString()

    }


}

