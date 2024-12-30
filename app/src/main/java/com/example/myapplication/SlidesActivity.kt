package com.example.myapplication
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.ActivitySlidesBinding

class SlidesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySlidesBinding
    private lateinit var onboardingItems: List<OnboardingItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlidesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })

        onboardingItems = listOf(
            OnboardingItem(
                title = "Explore the world easily",
                description = "to your desire",
                imageResId = R.drawable.slide_1
            ),
            OnboardingItem(
                title = "Reach the unknown spot",
                description = "to your destination",
                imageResId = R.drawable.slide_2
            ),
            OnboardingItem(
                title = "Make connects with explora",
                description = "To your dream trip",
                imageResId = R.drawable.slide_3
            )
        )

        val adapter = OnboardingItemsAdapter(onboardingItems)
        binding.onBoardingViewPager.adapter = adapter

        setupIndicators()
        binding.onBoardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicators(position)
            }
        })

        binding.btnGo.setOnClickListener {
            val currentItem = binding.onBoardingViewPager.currentItem
            if (currentItem + 1 < onboardingItems.size) {
                binding.onBoardingViewPager.setCurrentItem(currentItem + 1, true)
            } else {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setupIndicators() {
        val indicators = Array(onboardingItems.size) { ImageView(this) }
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8, 0, 8, 0)
        }
        indicators.forEach { indicator ->
            indicator.layoutParams = layoutParams
            binding.indicatorsContainer.addView(indicator)
        }
        updateIndicators(0)
    }

    private fun updateIndicators(position: Int) {
        for (i in 0 until binding.indicatorsContainer.childCount) {
            val indicator = binding.indicatorsContainer.getChildAt(i) as ImageView
            val drawableRes = if (i == position) {
                R.drawable.indicator_active_background
            } else {
                R.drawable.indicator_inactive_background
            }
            indicator.setImageDrawable(ContextCompat.getDrawable(this, drawableRes))
        }
    }
}