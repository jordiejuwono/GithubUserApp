package com.example.githubuserapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.databinding.GithubUserItemsBinding
import com.example.githubuserapp.model.GithubUser

class GithubUserAdapter(private val intentToProfileDetails: IntentToProfileDetails) : RecyclerView.Adapter<GithubUserAdapter.GithubUserViewHolder>() {

    val items: ArrayList<GithubUser> = arrayListOf()

    interface IntentToProfileDetails {
        fun intentToDetails(githubUser: GithubUser)
    }

    class GithubUserViewHolder(val binding: GithubUserItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        return GithubUserViewHolder(GithubUserItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        val currentPosition = items[position]
        holder.binding.tvName.text = currentPosition.name
        holder.binding.tvUsername.text = currentPosition.userName
        holder.binding.ivAvatar.setImageResource(currentPosition.avatar)
        holder.itemView.setOnClickListener {
            intentToProfileDetails.intentToDetails(currentPosition)
        }
    }

    override fun getItemCount(): Int = items.size
}