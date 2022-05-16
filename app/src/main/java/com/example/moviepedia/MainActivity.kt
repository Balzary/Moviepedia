package com.example.moviepedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepedia.model.Movie
import com.example.moviepedia.model.MovieResponse
import com.example.moviepedia.util.ApiInterface
import com.example.moviepedia.util.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var page = 1
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        val rv_movie_list: RecyclerView = findViewById(R.id.rv_movie_list)

        rv_movie_list.layoutManager = layoutManager
        rv_movie_list.setHasFixedSize(false)

        getMovieData { movies: List<Movie> ->
            rv_movie_list.adapter = MainAdapter(movies)
        }

        rv_movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItemCount: Int = layoutManager.childCount
                val pastVisibleItem: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total : Int? = rv_movie_list.adapter?.itemCount

                if(!isLoading){
                    if(visibleItemCount + pastVisibleItem > total!!){
                        page = page + 1
                        Log.d("Scroll", "$page")
                        getMovieData { movies: List<Movie> ->
                            rv_movie_list.adapter = MainAdapter(movies)
                        }
                    }
                }

                // não consegui terminar o scroll infinito a tempo da entrega. Mas obrigado pela oportunidade. Consegui fazer o carregamento de páginas extras, mas não consegui adicionar essas
                // páginas ao adapter, apenas recarreg-los

                super.onScrolled(recyclerView, dx, dy)

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                super.onScrollStateChanged(recyclerView, newState)
            }
        })


    }


    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        isLoading = true
        val apiService = ApiService.getInstance().create(ApiInterface::class.java)
        apiService.getMovieList(page).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)

            }

        })
        isLoading = false
    }
}

