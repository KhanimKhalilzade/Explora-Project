package com.example.myapplication

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTransparentStatusBar()
        setupCountryList()
    }

    private fun setTransparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setupCountryList() {
        val recyclerView: RecyclerView = findViewById(R.id.countryListView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CountryAdapter(
            countryList = getCountryData(),
            onCountryClicked = this::onCountrySelected,
            onOptionClicked = this::onOptionSelected
        )
    }

    private fun getCountryData(): List<Country> = listOf(
        Country(994, "Azerbaijan", R.drawable.azerbaijan, "Land of fire and cultural diversity."),
        Country(48, "Poland", R.drawable.poland, "Poland, the heart of Europe, with its rich history, vibrant culture, and welcoming people."),
        Country(33, "France", R.drawable.france, "Eiffel Tower and French culture."),
        Country(39, "Italy", R.drawable.italy, "Historical landmarks and Italian cuisine."),
        Country(81, "Japan", R.drawable.japan, "Technology and traditional culture."),
        Country(1, "USA", R.drawable.usa, "Diverse landscapes and Hollywood."),
        Country(55, "Brazil", R.drawable.brazil, "Carnival and Amazon rainforest."),
        Country(90, "Turkey", R.drawable.turkey, "Rich history, culture, and unique landscapes."),
        Country(7, "Russia", R.drawable.russia, "Sprawling landscapes and rich heritage.")
    )

    private fun onCountrySelected(country: Country) {
        showToastMessage("Selected: ${country.title} (Code: ${country.code})")
    }

    private fun onOptionSelected(country: Country, option: String) {
        showToastMessage("$option chosen for: ${country.title} (Code: ${country.code})")
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}