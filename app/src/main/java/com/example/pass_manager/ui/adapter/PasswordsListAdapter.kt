package com.example.pass_manager.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pass_manager.databinding.PasswordItemBinding
import com.example.pass_manager.domain.model.Password

class PasswordsListAdapter (
    private val clickListener : OnItemClickListener
) : ListAdapter<Password, PasswordsListAdapter.PasswordListViewHolder>(ListDiffCallback) {

    interface OnItemClickListener {
        fun onUserItemClick(password: Password)
    }

    inner class PasswordListViewHolder (
        private val binding: PasswordItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(password: Password) {
            binding.apply {
                root.setOnClickListener { clickListener.onUserItemClick(password) }
                siteTitle.text = password.siteName
                siteUserName.text = password.userName
                Glide.with(binding.root)
                    .load("https://besticon-demo.herokuapp.com/icon?url=${password.siteName}&size=64..64..64")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(siteIcon)
            }
        }
    }

    private object ListDiffCallback : DiffUtil.ItemCallback<Password>() {
        override fun areItemsTheSame(oldItem: Password, newItem: Password): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Password, newItem: Password): Boolean {
            return oldItem.siteName == newItem.siteName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordListViewHolder {
        return PasswordListViewHolder(
            PasswordItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PasswordListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}