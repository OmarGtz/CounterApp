package com.example.mycounterapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycounterapp.databinding.ItemCounterBinding
import com.example.mycounterapp.domain.model.Counter

class CounterAdapter(
    private val incrementOnClick: (Counter) -> Unit,
    private val decrementOnClick: (Counter) -> Unit,
    private val removeOnClick: (Counter) -> Unit,
    private val shareOnClick: (Counter) -> Unit
) : ListAdapter<Counter, CounterAdapter.CounterViewHolder>(PeopleDiffCallback) {

    class CounterViewHolder(val binding: ItemCounterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Counter,
            incrementOnClick: (Counter) -> Unit,
            decrementOnClick: (Counter) -> Unit,
            removeOnClick: (Counter) -> Unit,
            shareOnClick: (Counter) -> Unit
        ) {
            binding.root.setOnClickListener {
                onItemClicked(item, binding)
            }
            binding.title.text = item.title
            binding.counter.text = item.count.toString()
            binding.increment.setOnClickListener {
                incrementOnClick(item)
            }
            binding.decrement.setOnClickListener {
                decrementOnClick(item)
            }
            binding.remove.setOnClickListener {
                removeOnClick(item)
            }
            binding.share.setOnClickListener {
                shareOnClick(item)
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
        holder.bind(item, incrementOnClick, decrementOnClick, removeOnClick, shareOnClick)
    }
}

object PeopleDiffCallback : DiffUtil.ItemCallback<Counter>() {
    override fun areItemsTheSame(oldItem: Counter, newItem: Counter) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Counter, newItem: Counter) = oldItem == newItem
}