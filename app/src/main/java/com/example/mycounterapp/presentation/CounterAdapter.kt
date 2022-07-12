package com.example.mycounterapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycounterapp.databinding.ItemCounterBinding
import com.example.mycounterapp.domain.model.Counter

class CounterAdapter(
    private val incrementOnClick: () -> Unit,
    private val decrementOnClick: () -> Unit
) : ListAdapter<Counter, CounterAdapter.CounterViewHolder>(PeopleDiffCallback) {

    class CounterViewHolder(val binding: ItemCounterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Counter, incrementOnClick: () -> Unit, decrementOnClick: () -> Unit) {
            binding.root.setOnClickListener {
                onItemClicked(item, binding)
            }
            binding.title.text = item.title
            binding.counter.text = item.count.toString()
            binding.increment.setOnClickListener {
                incrementOnClick()
            }
            binding.decrement.setOnClickListener {
                decrementOnClick()
            }
        }

        fun onItemClicked(item: Counter, binding: ItemCounterBinding) {

        }

        companion object {
            fun from(parent: ViewGroup): CounterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCounterBinding.inflate(layoutInflater, parent, false)
                return CounterViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CounterViewHolder.from(parent)

    override fun onBindViewHolder(holder: CounterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, incrementOnClick, decrementOnClick)
    }
}

object PeopleDiffCallback : DiffUtil.ItemCallback<Counter>() {
    override fun areItemsTheSame(oldItem: Counter, newItem: Counter) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Counter, newItem: Counter) = oldItem == newItem
}