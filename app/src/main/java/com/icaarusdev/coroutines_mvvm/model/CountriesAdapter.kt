package com.icaarusdev.coroutines_mvvm.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.icaarusdev.coroutines_mvvm.databinding.ItemCountryBinding


class CountriesAdapter :
    ListAdapter<Country, CountriesAdapter.CountryViewHolder>(CountryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemBinding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(itemBinding)
    }


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class CountryViewHolder(val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Country) {
            binding.name.text = item.name
            binding.capital.text = item.capital

            Glide.with(binding.root.context)
                .load(item.flagPNG)
                .fitCenter()
                .into(binding.imageView)
        }
    }

    object CountryDiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}