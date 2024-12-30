package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.OnboardingItemContainerBinding


class OnboardingItemsAdapter(private val onboardingItems: List<OnboardingItem>) : RecyclerView.Adapter<OnboardingItemsAdapter.OnboardingItemViewHolder>() {

    inner class OnboardingItemViewHolder(val binding: OnboardingItemContainerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        val binding = OnboardingItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingItemViewHolder(binding)
    }

    override fun getItemCount(): Int = onboardingItems.size

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        val item = onboardingItems[position]
        holder.binding.materialTextView2.text = item.title
        holder.binding.materialTextView3.text = item.description
        holder.binding.imageView.setImageResource(item.imageResId)
    }

}