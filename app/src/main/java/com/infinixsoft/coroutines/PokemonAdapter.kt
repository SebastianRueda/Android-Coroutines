package com.infinixsoft.coroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pokemon.view.*
import java.util.zip.Inflater

class PokemonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var pokemonList: MutableList<Result> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(pokemonList[position])
    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(result: Result) {
            val number = result.url?.split("/")

            Glide.with(itemView)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + (adapterPosition + 1) +".png")
                .into(itemView.ivPokemon)

            itemView.tvPokemon.text = result.name?.capitalize()
        }
    }
}