package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(
    private val countryList: List<Country>,
    private val onCountryClicked: (Country) -> Unit,
    private val onOptionClicked: (Country, String) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val countryImage: ImageView = view.findViewById(R.id.Image)
        private val countryTitle: TextView = view.findViewById(R.id.Title)
        private val countryDetails: TextView = view.findViewById(R.id.Details)
        private val favoriteButton: ImageView = view.findViewById(R.id.favorite)
        private val shareButton: ImageView = view.findViewById(R.id.save)

        private var isFavorite = false

        fun initialize(
            country: Country,
            onCountryClicked: (Country) -> Unit,
            onOptionClicked: (Country, String) -> Unit
        ) {
            countryImage.setImageResource(country.imageId)
            countryTitle.text = country.title
            countryDetails.text = country.details

            favoriteButton.setImageResource(R.drawable.baseline_favorite_border_24)

            view.setOnClickListener {
                onCountryClicked(country)
            }

            favoriteButton.setOnClickListener {
                isFavorite = !isFavorite
                if (isFavorite) {
                    favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
                } else {
                    favoriteButton.setImageResource(R.drawable.baseline_favorite_border_24)
                }
                onOptionClicked(country, if (isFavorite) "Favorited" else "Unfavorited")
            }

            shareButton.setOnClickListener {
                onOptionClicked(country, "Shared")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.initialize(countryList[position], onCountryClicked, onOptionClicked)
    }

    override fun getItemCount(): Int = countryList.size
}