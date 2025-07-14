package com.example.testsuitmedia.ui.third

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testsuitmedia.data.response.DataItem
import com.example.testsuitmedia.databinding.ItemUsernameBinding

class UsernameAdapter(
    private val onItemClick: (DataItem) -> Unit
) : ListAdapter<DataItem, UsernameAdapter.UsernameViewHolder>(DIFF_CALLBACK) {
    inner class UsernameViewHolder(val binding: ItemUsernameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.apply {
                tvItemName.text = "${user.firstName} ${user.lastName}"
                tvItemEmail.text = user.email
                Glide
                    .with(itemView.context)
                    .load(user.avatar)
                    .circleCrop()
                    .into(binding.imgItemPhoto)

                root.setOnClickListener {
                    onItemClick(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsernameViewHolder {
        val binding = ItemUsernameBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UsernameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsernameViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean = oldItem == newItem
        }
    }
}