
package com.infinixsoft.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PokemonAdapter()
        rvPokemon.layoutManager = GridLayoutManager(this, 2)
        rvPokemon.adapter = adapter

        mainViewModel.getPokemon().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        rvPokemon.visibility = View.VISIBLE
                        progressbar.visibility = View.GONE

                        resource.data?.let {
                            adapter.pokemonList.addAll(it.results as MutableList)
                            adapter.notifyDataSetChanged()
                        }
                    }
                    Status.ERROR -> {
                        resource.message?.let {
                            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.LOADING -> {
                        rvPokemon.visibility = View.GONE
                        progressbar.visibility = View.VISIBLE
                    }
                }
            }
        })

    }
}
